package com.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String introduction;
    private String mainText;
    private String category;
    private String author;
    private Date createTime;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Comment> commentList;

    public Article() {
    }

    public Article(String title, String introduction, String mainText, String category) {
        this.title = title;
        this.introduction = introduction;
        this.mainText = mainText;
        this.category = category;
        this.createTime = new Date();
    }

    public Article(Integer id, String title, String introduction, String mainText, String category) {
        this.id = id;
        this.title = title;
        this.introduction = introduction;
        this.mainText = mainText;
        this.category = category;
        this.createTime = new Date();
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
