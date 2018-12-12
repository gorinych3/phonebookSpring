package ru.egor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.egor.model.Car;
import ru.egor.model.PetAnimal;
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

    @RequestMapping(value = "/searchById", method = {RequestMethod.GET})
    public String searchByIdGet(@ModelAttribute(value = "id") String id){
            System.out.println("Запуск сервлета searchById");
         return "searchById";
    }

    @RequestMapping(value = "/searchById", method = {RequestMethod.POST})
    public String searchByIdPost(@ModelAttribute(value = "id") String id, Model model){
            int user_id = Integer.parseInt(id);
            System.out.println("Запуск сервлета searchById");
            User user = userService.getUserById(user_id);
            if(user!=null) {
                model.addAttribute(user);
                return "searchById";
            }else return "redirect:searchById";
    }

    @RequestMapping(value = "/searchByFirstName", method = {RequestMethod.GET})
    public String searchByFirstNameGet(@ModelAttribute(value = "f_name") String f_name){
        System.out.println("Запуск сервлета searchByFirstName");
        return "searchByFirstName";
    }

    @RequestMapping(value = "/searchByFirstName", method = {RequestMethod.POST})
    public String searchByFirstNamePost(@ModelAttribute(value = "f_name") String f_name, Model model){
        List<User> users;
        System.out.println("Запуск сервлета searchByFirstName");
        users = userService.searchByFirstName(f_name);
        System.out.println(f_name);
        System.out.println(users);
        if(users!=null) {
            System.out.println("users is not null");
            model.addAttribute("users",users);
            return "searchByFirstName";
        }else return "redirect:searchByFirstName";
    }

    @RequestMapping(value = "/searchByMobilePhone", method = {RequestMethod.GET})
    public String searchByMobilePhoneGet(@ModelAttribute(value = "m_phone") String m_phone){
        System.out.println("Запуск сервлета searchByMobilePhone");
        return "searchByMobilePhone";
    }

    @RequestMapping(value = "/searchByMobilePhone", method = {RequestMethod.POST})
    public String searchByMobilePhonePost(@ModelAttribute(value = "m_phone") String m_phone, Model model){
        System.out.println("Запуск сервлета searchByMobilePhone");
        List<User> users;
        users = userService.getUserMobilePhone(m_phone);
        if(users!=null) {
            model.addAttribute("users",users);
            return "searchByMobilePhone";
        }else return "redirect:searchByMobilePhone";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.GET})
    public String addUserGet(@ModelAttribute(value = "f_name") String f_name){
        System.out.println("Запуск сервлета addUserGet");
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    public String addUserPost(User user, Model model){
        System.out.println("Запуск сервлета addUserPost");
        System.out.println(user.toString());
        userService.add(user);
        System.out.println(user.getF_name());
        model.addAttribute("f_name",user.getF_name());
        return "redirect:addUser";
    }

    @RequestMapping("/add")
    public String add(){
        System.out.println("Запуск сервлета add");
        return "add";
    }

    @RequestMapping(value = "/addPet", method = {RequestMethod.GET})
    public String addPetGet(@ModelAttribute(value = "pet_name") String f_name){
        System.out.println("Запуск сервлета addPet");
        return "addPet";
    }

    @RequestMapping(value = "/addPet", method = {RequestMethod.POST})
    public String addPetPost(@ModelAttribute("id") String id, PetAnimal petAnimal, Model model){
        int user_id = Integer.parseInt(id);
        System.out.println("Запуск сервлета addPet");
        System.out.println(petAnimal.toString());
        userService.addPet(user_id, petAnimal);
        System.out.println(petAnimal.getPetName());
        model.addAttribute("pet_name",petAnimal.getPetName());
        return "redirect:addPet";
    }

}
