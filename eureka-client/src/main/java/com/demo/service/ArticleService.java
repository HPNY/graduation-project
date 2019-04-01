package com.demo.service;

import com.demo.dao.ArticleRepository;
import com.demo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleService {

    public static final int pageSize = 10;

    public static final String sortProperties = "id";

    @Autowired
    private ArticleRepository articleRepository;


    public Article addArticle(Article article) {
        Date date = new Date();
        article.setCreateTime(date);
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

    public Page<Article> findAllArticle(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAll(pageable);
    }

    public Page<Article> findAllByTitleContaining(String title, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByTitleContaining(pageable, title);
    }

    public Article findById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Page<Article> findAllByCategory(String category, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByCategory(pageable, category);
    }

    public Page<Article> findAllByCategoryAndTitleContaining(Integer pageCount, String category, String title) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return articleRepository.findAllByCategoryAndTitleContaining(pageable, category, title);
    }
}
