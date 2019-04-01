package com.demo.controller;

import com.demo.entity.Article;
import com.demo.entity.User;
import com.demo.service.ArticleService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ReceptionController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/reception/login")
    public String login() {
        return "/reception/login";
    }

    @RequestMapping("/reception/register")
    public String register() {
        return "/reception/register";
    }

    @RequestMapping("/reception/article_add")
    public String add() {
        return "/reception/article_add";
    }

    @RequestMapping("/reception/findById")
    public String findById(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/reception/article_detail";
    }

    @RequestMapping("/reception/findArticle")
    @ResponseBody
    public Article findArticle(Integer id) {
        Article article = articleService.findById(id);
        return article;
    }


    @RequestMapping("/reception/addArticle")
    @ResponseBody
    public String addArticle(Article article, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String author = user.getNickname();
        article.setAuthor(author);
        articleService.addArticle(article);
        return "success";
    }

    @RequestMapping("/reception/index")
    public String findAll(Integer pageCount, Model model) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Article> page = articleService.findAllArticle(pageCount);
        Integer total = page.getTotalPages();
        model.addAttribute("page", page);
        model.addAttribute("pageCount", pageCount + 1);
        model.addAttribute("total", total);
        return "/reception/index";
    }

    @RequestMapping("/reception/updateArticle")
    public String updateArticle(Article article, Model model) {
        articleService.updateArticle(article);
        return findAll(0, model);
    }

    @RequestMapping("/reception/deleteArticle")
    public String deleteArticle(Integer id, Model model) {
        articleService.deleteArticle(id);
        return findAll(0, model);
    }

    @RequestMapping("/reception/findAllByCategory")
    @ResponseBody
    public Page findAllByCategory(String category, Integer pageCount) {
        Page<Article> page;
        if (pageCount == null) {
            pageCount = 0;
        }
        if (category.equals("全部分类")) {
            page = articleService.findAllArticle(pageCount);
        } else
            page = articleService.findAllByCategory(category, pageCount);
        return page;
    }

    @RequestMapping("/reception/findAllByTitle")
    public String findAllByTitle(String title, Integer pageCount, Model model) {
        if (pageCount == null) {
            pageCount = 0;
        }
        Page<Article> page = articleService.findAllByTitleContaining(title, pageCount);
        return "success";
    }

    @RequestMapping("/reception/changeInformation")
    @ResponseBody
    public String changeInformation(String id, String nickname, String actualName, Integer sex, Integer year,
                                    Integer month, Integer day, String introduction) {
        User user = userService.findById(Integer.parseInt(id));
        user.setNickname(nickname);
        user.setActualName(actualName);
        user.setSex(sex);
        Date birthday = new Date(year - 1900, month - 1, day);
        user.setBirthday(birthday);
        user.setIntroduction(introduction);
        user.getPassword();
        userService.updateUser(user);
        return "success";
    }

    @RequestMapping("/reception/Containing")
    @ResponseBody
    public Page<Article> findAllByTitleContaining(Integer pageCount, String title) {
        Page<Article> page = articleService.findAllByTitleContaining(title, pageCount);
        return page;
    }

    @RequestMapping("/reception/findall")
    @ResponseBody
    public Page<Article> findall(Integer pageCount, String title, String category) {
        Page<Article> page;
        if (title == null || title.equals("")) {
            page = articleService.findAllByCategory(category, pageCount);
        } else if (category.equals("全部分类")) {
            page = articleService.findAllByTitleContaining(title, pageCount);
        } else {
            page = articleService.findAllByCategoryAndTitleContaining(pageCount, title, category);
        }
        return page;
    }

    @RequestMapping("/reception/personal")
    public String personal(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userService.findById(user.getId());
        model.addAttribute("user", user);
        return "/reception/personal";
    }
}

