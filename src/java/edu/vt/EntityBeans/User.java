/*
 * Created by Muhammad Afiq Yusof on 2018.11.28  * 
 * Copyright © 2018 Muhammad Afiq Yusof. All rights reserved. * 
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author muhda
 */
@Entity
@Table(name = "User")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByFirstName", query = "SELECT u FROM User u WHERE u.firstName = :firstName")
    , @NamedQuery(name = "User.findByMiddleName", query = "SELECT u FROM User u WHERE u.middleName = :middleName")
    , @NamedQuery(name = "User.findByLastName", query = "SELECT u FROM User u WHERE u.lastName = :lastName")
    , @NamedQuery(name = "User.findByCcNumber", query = "SELECT u FROM User u WHERE u.ccNumber = :ccNumber")
    , @NamedQuery(name = "User.findByCcExpires", query = "SELECT u FROM User u WHERE u.ccExpires = :ccExpires")
    , @NamedQuery(name = "User.findByCcSecurityCode", query = "SELECT u FROM User u WHERE u.ccSecurityCode = :ccSecurityCode")
    , @NamedQuery(name = "User.findByAddress1", query = "SELECT u FROM User u WHERE u.address1 = :address1")
    , @NamedQuery(name = "User.findByAddress2", query = "SELECT u FROM User u WHERE u.address2 = :address2")
    , @NamedQuery(name = "User.findByCity", query = "SELECT u FROM User u WHERE u.city = :city")
    , @NamedQuery(name = "User.findByState", query = "SELECT u FROM User u WHERE u.state = :state")
    , @NamedQuery(name = "User.findByZipcode", query = "SELECT u FROM User u WHERE u.zipcode = :zipcode")
    , @NamedQuery(name = "User.findBySecurityQuestionNumber", query = "SELECT u FROM User u WHERE u.securityQuestionNumber = :securityQuestionNumber")
    , @NamedQuery(name = "User.findBySecurityAnswer", query = "SELECT u FROM User u WHERE u.securityAnswer = :securityAnswer")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 32)
    @Column(name = "middle_name")
    private String middleName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 19)
    @Column(name = "cc_number")
    private String ccNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "cc_expires")
    private String ccExpires;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "cc_security_code")
    private String ccSecurityCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "address1")
    private String address1;
    @Size(max = 128)
    @Column(name = "address2")
    private String address2;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "zipcode")
    private String zipcode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "security_question_number")
    private int securityQuestionNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "security_answer")
    private String securityAnswer;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "userId")
    private Collection<UserAlbum> userAlbumCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<UserPhoto> userPhotoCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<UserBook> userBookCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<UserFile> userFileCollection;
    @OneToMany(mappedBy = "userId")
    private Collection<UserMovie> userMovieCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String password, String firstName, String lastName, String ccNumber, String ccExpires, String ccSecurityCode, String address1, String city, String state, String zipcode, int securityQuestionNumber, String securityAnswer, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ccNumber = ccNumber;
        this.ccExpires = ccExpires;
        this.ccSecurityCode = ccSecurityCode;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.securityQuestionNumber = securityQuestionNumber;
        this.securityAnswer = securityAnswer;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpires() {
        return ccExpires;
    }

    public void setCcExpires(String ccExpires) {
        this.ccExpires = ccExpires;
    }

    public String getCcSecurityCode() {
        return ccSecurityCode;
    }

    public void setCcSecurityCode(String ccSecurityCode) {
        this.ccSecurityCode = ccSecurityCode;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getSecurityQuestionNumber() {
        return securityQuestionNumber;
    }

    public void setSecurityQuestionNumber(int securityQuestionNumber) {
        this.securityQuestionNumber = securityQuestionNumber;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<UserAlbum> getUserAlbumCollection() {
        return userAlbumCollection;
    }

    public void setUserAlbumCollection(Collection<UserAlbum> userAlbumCollection) {
        this.userAlbumCollection = userAlbumCollection;
    }

    @XmlTransient
    public Collection<UserPhoto> getUserPhotoCollection() {
        return userPhotoCollection;
    }

    public void setUserPhotoCollection(Collection<UserPhoto> userPhotoCollection) {
        this.userPhotoCollection = userPhotoCollection;
    }

    @XmlTransient
    public Collection<UserBook> getUserBookCollection() {
        return userBookCollection;
    }

    public void setUserBookCollection(Collection<UserBook> userBookCollection) {
        this.userBookCollection = userBookCollection;
    }

    @XmlTransient
    public Collection<UserFile> getUserFileCollection() {
        return userFileCollection;
    }

    public void setUserFileCollection(Collection<UserFile> userFileCollection) {
        this.userFileCollection = userFileCollection;
    }

    @XmlTransient
    public Collection<UserMovie> getUserMovieCollection() {
        return userMovieCollection;
    }

    public void setUserMovieCollection(Collection<UserMovie> userMovieCollection) {
        this.userMovieCollection = userMovieCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.User[ id=" + id + " ]";
    }
    
    public String ccNumberLast4Digits() {
        // Return the substring from the 12th character to the last character.
        // Credit card number is 16 digits long going from position 0 to 15.
        // Positions: 0123456789012345 --> 2345 Last 4 digits.
        return getCcNumber().substring(12);
    }
    
}
