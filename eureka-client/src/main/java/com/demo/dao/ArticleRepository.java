package com.demo.dao;

import com.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Page<Article> findAllByTitleContaining(Pageable pageable, String title);

    Page<Article> findAllByCategory(Pageable pageable, String category);

    Page<Article> findAllByCategoryAndTitleContaining(Pageable pageable, String category, String title);

    List<Article> findAllById(List<Integer> id);
}
