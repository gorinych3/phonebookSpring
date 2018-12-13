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
        users = userService.list();
        model.addAttribute("user",users);
        return "selectAll";
    }

    @RequestMapping(value = "/searchById", method = {RequestMethod.GET})
    public String searchByIdGet(){
            System.out.println("Запуск сервлета searchById");
         return "searchById";
    }

    @RequestMapping(value = "/searchById", method = {RequestMethod.POST})
    public String searchByIdPost(@ModelAttribute(value = "id") String id, Model model){
            int user_id = Integer.parseInt(id);
            String operation = "searchById";
            System.out.println("Запуск сервлета searchById");
            User user = userService.getUserById(user_id);
            List<User> users = new ArrayList<>();
            if(user!=null) {
                users.add(user);
                model.addAttribute("operationName",operation);
                model.addAttribute("user",users);
                return "result";
            }else return "redirect:searchById";
    }

    @RequestMapping(value = "/searchByFirstName", method = {RequestMethod.GET})
    public String searchByFirstNameGet(){
        System.out.println("Запуск сервлета searchByFirstName");
        return "searchByFirstName";
    }

    @RequestMapping(value = "/searchByFirstName", method = {RequestMethod.POST})
    public String searchByFirstNamePost(@ModelAttribute(value = "f_name") String f_name, Model model){
        List<User> users;
        System.out.println("Запуск сервлета searchByFirstName");
        users = userService.searchByFirstName(f_name);
        String operation = "searchByFirstName";
        if(users!=null) {
            model.addAttribute("operationName",operation);
            model.addAttribute("user",users);
            return "result";
        }else return "redirect:searchByFirstName";
    }

    @RequestMapping(value = "/searchByMobilePhone", method = {RequestMethod.GET})
    public String searchByMobilePhoneGet(){
        System.out.println("Запуск сервлета searchByMobilePhone");
        return "searchByMobilePhone";
    }

    @RequestMapping(value = "/searchByMobilePhone", method = {RequestMethod.POST})
    public String searchByMobilePhonePost(@ModelAttribute(value = "m_phone") String m_phone, Model model){
        System.out.println("Запуск сервлета searchByMobilePhone");
        String operation = "searchByMobilePhone";
        List<User> users;
        users = userService.getUserMobilePhone(m_phone);
        System.out.println(m_phone);
        System.out.println(users.size());
        System.out.println(users.get(0).getM_phone());
        if(users!=null) {
            model.addAttribute("operationName",operation);
            model.addAttribute("user",users);
            return "result";
        }else return "redirect:searchByMobilePhone";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.GET})
    public String addUserGet(){
        System.out.println("Запуск сервлета addUserGet");
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    public String addUserPost(User user, Model model){
        System.out.println("Запуск сервлета addUserPost");
        String operation = "add user";
        userService.add(user);
        System.out.println(user.getF_name());
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping("/add")
    public String add(){
        System.out.println("Запуск сервлета add");
        return "add";
    }

    @RequestMapping(value = "/addPet", method = {RequestMethod.GET})
    public String addPetGet(){
        System.out.println("Запуск сервлета addPet");
        return "addPet";
    }

    @RequestMapping(value = "/addPet", method = {RequestMethod.POST})
    public String addPetPost(@ModelAttribute(value = "id") String id, PetAnimal petAnimal, Model model){
        int user_id = Integer.parseInt(id);
        System.out.println("Запуск сервлета addPet");
        String operation = "add pet";
        userService.addPet(user_id, petAnimal);
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.GET})
    public String updateUserGet(){
        System.out.println("Запуск сервлета updateUserGet");
        return "updateUser";
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST})
    public String updateUserPost(User user, Model model){
        System.out.println("Запуск сервлета updateUserPost");
        String operation = "update user";
        userService.update(user);
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping("/updateList")
    public String updateList(){
        System.out.println("Запуск сервлета updateList");
        return "updateList";
    }


    @RequestMapping(value = "/updatePet", method = {RequestMethod.GET})
    public String updatePetGet(){
        System.out.println("Запуск сервлета updatePet");
        return "updatePet";
    }

    @RequestMapping(value = "/updatePet", method = {RequestMethod.POST})
    public String updatePetPost(@ModelAttribute(value = "id") String id, PetAnimal petAnimal, Model model){
        int user_id = Integer.parseInt(id);
        System.out.println("Запуск сервлета updatePet");
        String operation = "update pet";
        userService.updatePet(user_id,petAnimal.getPet_id(), petAnimal);
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping("/delete")
    public String deleteList(){
        System.out.println("Запуск сервлета delete");
        return "delete";
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.GET})
    public String deleteByIdGet(){
        System.out.println("Запуск сервлета deleteById");
        return "deleteUser";
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.POST})
    public String deleteByIdPost(@ModelAttribute(value = "id") String id, Model model){
        int user_id = Integer.parseInt(id);
        System.out.println("Запуск сервлета deleteById");
        String operation = "delete user";
        userService.deleteUser(user_id);
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping(value = "/deletePet", method = {RequestMethod.GET})
    public String deletePetGet(){
        System.out.println("Запуск сервлета deletePet");
        return "deletePet";
    }

    @RequestMapping(value = "/deletePet", method = {RequestMethod.POST})
    public String deletePetPost(@ModelAttribute(value = "user_id") String us_id, @ModelAttribute(value = "pet_id") String p_id, Model model){
        int user_id = Integer.parseInt(us_id);
        int pet_id = Integer.parseInt(p_id);
        String operation = "delete pet";
        System.out.println("Запуск сервлета deletePet");
        userService.deletePet(user_id, pet_id);
        model.addAttribute("operationName",operation);
        return "result";
    }

    @RequestMapping("/result")
    public String result(){
        System.out.println("Запуск сервлета delete");
        return "result";
    }
}
