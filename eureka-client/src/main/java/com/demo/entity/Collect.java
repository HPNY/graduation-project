package com.demo.entity;


import javax.persistence.*;

@Entity
public class Collect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer articleId;

    public Collect() {
    }

    public Collect(Integer userId, Integer articleId) {
        this.userId = userId;
        this.articleId = articleId;
    }

    public Collect(Integer id, Integer userId, Integer articleId) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
