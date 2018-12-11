package ru.egor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.egor.dao.UserDAO;
import ru.egor.dao.UserDAOImpl;
import ru.egor.model.PetAnimal;
import ru.egor.model.User;

import java.util.List;

@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userDAOImpl")
    private UserDAO userDAO;

    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }
    @Transactional
    public List<User> list() {
        System.out.println("Запуск метода selectAll в UserServiceImpl");
        return userDAO.list();
    }

    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userDAO.deleteUser(id);
    }

    @Transactional
    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    @Transactional
    public User getUserMobilePhone(String m_phone) {
        return userDAO.getUserMobilePhone(m_phone);
    }

    @Transactional
    public List<User> searchByFirstName(String first_name) {
        return userDAO.searchByFirstName(first_name);
    }

    @Transactional
    public void addPet(int id, PetAnimal petAnimal) {
        userDAO.addPet(id, petAnimal);
    }

    @Transactional
    public void updatePet(int user_id, int pet_id, PetAnimal petAnimal) {
        userDAO.updatePet(user_id, pet_id, petAnimal);
    }

    @Transactional
    public void deletePet(int user_id, int pet_id) {
        userDAO.deletePet(user_id, pet_id);
    }
}
