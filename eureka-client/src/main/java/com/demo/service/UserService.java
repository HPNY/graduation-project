package com.demo.service;

import com.demo.dao.RoleRepository;
import com.demo.dao.UserRepository;
import com.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static final int pageSize = 10;

    public static final String sortProperties = "id";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User addUser(User user) {
        String username = user.getUsername();
        //判断用户名是否存在
        if (userRepository.findByUsername(username) == null) {
            //进行密码加密
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            //授予普通用户权限
            List list = new ArrayList();
            list.add(roleRepository.findById(3).orElse(null));
            user.setAuthorities(list);
            //保存
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Transactional
    public User updateUser(User user) {

        return userRepository.save(user);
    }

    @Transactional
    public void delUser(Integer id) {
        userRepository.deleteById(id);
    }

    public Page<User> findAllUser(Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return userRepository.findAll(pageable);
    }

    public Page<User> findAllByUsernameContaining(String username, Integer pageCount) {
        Sort sort = new Sort(Sort.Direction.ASC, sortProperties);
        Pageable pageable = PageRequest.of(pageCount, pageSize, sort);
        return userRepository.findAllByUsernameContaining(pageable, username);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
