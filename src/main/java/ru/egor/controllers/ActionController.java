package ru.egor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egor.model.User;
import ru.egor.service.UserService;
import ru.egor.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ActionController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        System.out.println("Запуск сервлета index");
        return "index";
    }

    @RequestMapping(value = "/")
    public String home() {
        System.out.println("Запуск сервлета /");
        return "index";
    }

    @RequestMapping("/list")
    public String list(){
        System.out.println("Запуск сервлета list");
        return "list";
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public String selectAll(Model model){
        System.out.println("Запуск сервлета selectAll");
        List<User> users;
        users = (List<User>) userService.list();
        model.addAttribute("user",users);
        return "selectAll";
    }
    @RequestMapping(value = "/searchById", method = {RequestMethod.GET,RequestMethod.POST})
    public String searchById(@RequestParam(value = "id") String id, Model model){
//        int user_id = Integer.parseInt(id);
        System.out.println("Запуск сервлета searchById");
        System.out.println("Parameter id = " + id);
//        System.out.println("Parameter id = " + user_id);
//        System.out.println("Parameter model = " + model);
//        model.addAttribute(userService.getUserById(user_id));
        return "searchById";
    }
}
