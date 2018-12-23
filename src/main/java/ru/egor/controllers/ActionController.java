package ru.egor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.egor.model.PetAnimal;
import ru.egor.model.User;
import ru.egor.model.UsersPet;
import ru.egor.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        System.out.println("Запуск сервлета searchById");
        String operation = "searchById";
        String errorMessage = "id";
        int user_id;
        try {
            user_id = Integer.parseInt(id);
        }catch (NumberFormatException e){
            return "errorPage";
        }
            User user = userService.getUserById(user_id);
            List<User> users = new ArrayList<>();
        System.out.println(user);
        model.addAttribute("operationName",operation);
        model.addAttribute("user",users);
        model.addAttribute("errorMessage", errorMessage);
            if(user!=null) {
                users.add(user);
                return "result";
            }else return "errorPage";
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
        String errorMessage = "first name";
        model.addAttribute("operationName",operation);
        model.addAttribute("user",users);
        model.addAttribute("errorMessage", errorMessage);
        if(users.size()!=0) {
            return "result";
        }else return "errorPage";
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
        String errorMessage = "mobile phone";
        List<User> users;
        users = userService.getUserMobilePhone(m_phone);
        model.addAttribute("operationName",operation);
        model.addAttribute("user",users);
        model.addAttribute("errorMessage", errorMessage);
        if(users.size()!=0) {
            return "result";
        }else return "errorPage";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.GET})
    public String addUserGet(){
        System.out.println("Запуск сервлета addUserGet");
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    public String addUserPost(User user,PetAnimal petAnimal, Model model){
        System.out.println("Запуск сервлета addUserPost");
        String operation = "add user";
        String errorMessage = "empty fields";
        model.addAttribute("operationName", operation);
        model.addAttribute("errorMessage", errorMessage);
        if(!user.getF_name().equals("")&&!user.getL_name().equals("")&&!user.getAddress().equals("")) {
            Set <UsersPet> usersPets = new HashSet<>();
            UsersPet up = new UsersPet();
            up.setUser(user);
            up.setPetAnimal(petAnimal);
            up.setUp_id(user.getId());
            usersPets.add(up);
            user.setUsersPets(usersPets);
            System.out.println(user.toString());
            System.out.println(petAnimal.toString());
            System.out.println(user.getUsersPets().size());
            userService.add(user);
            model.addAttribute("user", user);
            return "resultSingle";
        }else return "errorPage";
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
        System.out.println("Запуск сервлета addPet");
        String operation = "add pet";
        String errorMessage = "id";
        model.addAttribute("operationName",operation);
        model.addAttribute("errorMessage", errorMessage);
        int user_id;
        try {
            user_id = Integer.parseInt(id);
        }catch (NumberFormatException e){
            return "errorPage";
        }
        List<User> users = userService.list();
        for(User us : users){
            if(us.getId()==user_id){
                userService.addPet(user_id, petAnimal);
                return "result";
            }
        }
        return "errorPage";
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
        String errorMessage = "id or input empty fields";
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("operationName",operation);
        List<User> users = userService.list();
        for(User us : users){
            if(us.getId()==user.getId()&&(!user.getF_name().equals("")&&!user.getL_name().equals("")&&!user.getAddress().equals(""))){
                System.out.println("условие выполняется");
                userService.update(user);
                return "resultSingle";
            }
        }
        return "errorPage";
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
        System.out.println("Запуск сервлета deleteById");
        String operation = "delete user";
        model.addAttribute("operationName",operation);
        String errorMessage = "id";
        model.addAttribute("errorMessage", errorMessage);
        int user_id;
        try {
            user_id = Integer.parseInt(id);
        }catch (NumberFormatException e){
            return "errorPage";
        }
        List<User> users = userService.list();
        for(User us : users){
            if(us.getId()==user_id){
                model.addAttribute("user",us);
                userService.deleteUser(user_id);
                return "resultSingle";
            }
        }
        return "errorPage";
    }

    @RequestMapping(value = "/deletePet", method = {RequestMethod.GET})
    public String deletePetGet(){
        System.out.println("Запуск сервлета deletePet");
        return "deletePet";
    }

    @RequestMapping(value = "/deletePet", method = {RequestMethod.POST})
    public String deletePetPost(@ModelAttribute(value = "user_id") String us_id, @ModelAttribute(value = "pet_id") String p_id, Model model){
        System.out.println("Запуск сервлета deletePet");
        String operation = "delete pet";
        model.addAttribute("operationName", operation);
        String errorMessage = "id";
        model.addAttribute("errorMessage", errorMessage);
        if(us_id.equals("")||p_id.equals("")) return "errorPage";
        int user_id;
        int pet_id;
        try {
            user_id = Integer.parseInt(us_id);
            pet_id = Integer.parseInt(p_id);
        }catch (NumberFormatException e){
            return "errorPage";
        }
        List<User> users = userService.list();
        Set<UsersPet> uspet;
        for(User us : users){
            uspet = us.getUsersPets();
            if(us.getId()==user_id){
                for(UsersPet usersPet : uspet) {
                    if(usersPet.getPetAnimal().getPet_id()==pet_id) {
                        model.addAttribute("user", us);
                        userService.deletePet(user_id, pet_id);
                        return "resultSingle";
                    }
                }
            }
        }
        return "errorPage";
    }

    @RequestMapping("/result")
    public String result(){
        System.out.println("Запуск сервлета delete");
        return "result";
    }

    @RequestMapping("/errorPage")
    public String errorPage(){
        System.out.println("Запуск сервлета delete");
        return "errorPage";
    }
}
