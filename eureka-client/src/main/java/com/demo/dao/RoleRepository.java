package com.demo.dao;

import com.demo.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Page<Role> findAllByAuthorityContaining(Pageable pageable, String authority);
}
