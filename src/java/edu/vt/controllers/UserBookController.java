/*
 * Created by Muhammad Afiq Yusof on 2018.11.28  * 
 * Copyright © 2018 Muhammad Afiq Yusof. All rights reserved. * 
 */
package edu.vt.controllers;

import edu.vt.EntityBeans.Media;
import edu.vt.EntityBeans.PublicBook;
import edu.vt.EntityBeans.User;
import edu.vt.EntityBeans.UserBook;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.UserBookFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("userBookController")
@SessionScoped
public class UserBookController implements Serializable {
    
    private final String goodreadsApiKey = "api_key=RRjzK1W8TyINyDz8VzOqcA";

    @EJB
    private edu.vt.FacadeBeans.UserBookFacade ejbFacade;
    private List<UserBook> items = null;
    private UserBook selected;

    public UserBookController() {
    }

    public UserBook getSelected() {
        return selected;
    }

    public void setSelected(UserBook selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserBookFacade getFacade() {
        return ejbFacade;
    }

    public UserBook prepareCreate() {
        selected = new UserBook();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserBookCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void create(User user) {
        selected.setUserId(user);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserBookCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserBookUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserBookDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserBook> getItems() {
        if (items == null) {
            items = getFacade().findByUserId((int) Methods.sessionMap().get("user_id"));
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UserBook getUserBook(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<UserBook> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UserBook> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = UserBook.class)
    public static class UserBookControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserBookController controller = (UserBookController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userBookController");
            return controller.getUserBook(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserBook) {
                UserBook o = (UserBook) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserBook.class.getName()});
                return null;
            }
        }

    }

    public void removeIfMatchType(List<Media> medias) {
        for (int i = 0; i < medias.size(); i++) {
            Media curr = medias.get(i);
            if (curr.getType().equals("Book")) {
                UserBook foundBook = getFacade().find(curr.getUserVersionId());
                setSelected(foundBook);
                destroy();
            }
        }
    }
    
    public boolean isOwnItem(PublicBook pubBook) {
        UserBook userBook = getFacade().findByUserIdAndUserVersionId((int) Methods.sessionMap().get("user_id"), pubBook.getUserVersionId());
        if (userBook == null)
            return false;
        return true;
    }
}
