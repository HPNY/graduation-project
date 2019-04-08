package com.demo.dao;

import com.demo.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectRepository extends JpaRepository<Collect, Integer> {
    List<Collect> findAllByUserId(Integer userId);

    Collect findCollectByUserIdAndArticleId(Integer userId,Integer articleId);
}
