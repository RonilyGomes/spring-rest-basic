package br.edu.ifpb.pd.news.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
@XmlRootElement(name = "news")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name="TB_NEWS")
public class News implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @XmlElement
    private long id;
    
    @XmlElement
    private String author;
    
    @XmlElement
    private String title;
    
    @JsonFormat(pattern="dd/MM/Y HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement
    private Date postDate;
    
    @Column(columnDefinition = "TEXT")
    @XmlElement
    private String content;
    
    public News() {}

    public News(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.postDate = new Date();
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}