/*
 * Created by Muhammad Afiq Yusof on 2018.11.28  * 
 * Copyright © 2018 Muhammad Afiq Yusof. All rights reserved. * 
 */
package edu.vt.EntityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author muhda
 */
@Entity
@Table(name = "PublicBook")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicBook.findAll", query = "SELECT p FROM PublicBook p")
    , @NamedQuery(name = "PublicBook.findById", query = "SELECT p FROM PublicBook p WHERE p.id = :id")
    , @NamedQuery(name = "PublicBook.findByTitle", query = "SELECT p FROM PublicBook p WHERE p.title = :title")
    , @NamedQuery(name = "PublicBook.findByAuthor", query = "SELECT p FROM PublicBook p WHERE p.author = :author")
    , @NamedQuery(name = "PublicBook.findByPublicationYear", query = "SELECT p FROM PublicBook p WHERE p.publicationYear = :publicationYear")
    , @NamedQuery(name = "PublicBook.findByIsbn", query = "SELECT p FROM PublicBook p WHERE p.isbn = :isbn")
    , @NamedQuery(name = "PublicBook.findByGenres", query = "SELECT p FROM PublicBook p WHERE p.genres = :genres")
    , @NamedQuery(name = "PublicBook.findByAveragePrice", query = "SELECT p FROM PublicBook p WHERE p.averagePrice = :averagePrice")})
public class PublicBook implements Serializable {

    @Column(name = "user_version_id")
    private Integer userVersionId;

    @Basic(optional = false)
    @NotNull
    @Column(name = "isbn")
    private double isbn;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "average_price")
    private Float averagePrice;

    @Column(name = "user_id")
    private Integer userId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "author")
    private String author;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication_year")
    private int publicationYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 128)
    @Column(name = "genres")
    private String genres;

    public PublicBook() {
    }

    public PublicBook(Integer id) {
        this.id = id;
    }

    public PublicBook(Integer id, String title, String author, int publicationYear, int isbn, String genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
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
        if (!(object instanceof PublicBook)) {
            return false;
        }
        PublicBook other = (PublicBook) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.vt.EntityBeans.PublicBook[ id=" + id + " ]";
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getIsbn() {
        return isbn;
    }

    public void setIsbn(double isbn) {
        this.isbn = isbn;
    }

    public Float getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Float averagePrice) {
        this.averagePrice = averagePrice;
    }

    public Integer getUserVersionId() {
        return userVersionId;
    }

    public void setUserVersionId(Integer userVersionId) {
        this.userVersionId = userVersionId;
    }
    
}
