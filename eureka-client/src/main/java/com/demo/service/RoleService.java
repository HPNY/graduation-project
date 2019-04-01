package com.demo.service;

import com.demo.dao.RoleRepository;
import com.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    public static final int pageSize = 10;

    public static final String sortProperties = "id";

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    public void delRole(Integer id) {
        roleRepository.deleteById(id);
    }

    public Page<Role> findAllByNameContaining(String name, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return roleRepository.findAllByAuthorityContaining(pageable, name);
    }

    public Page<Role> findAllRoleByPage(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return roleRepository.findAll(pageable);

    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
