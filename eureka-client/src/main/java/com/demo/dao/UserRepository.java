package com.demo.dao;

import com.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User, Integer> {
    User findByUsername(String username);

    Page<User> findAllByUsernameContaining(Pageable pageable, String username);
}
