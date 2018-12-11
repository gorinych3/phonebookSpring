package ru.egor.dao;

import org.springframework.stereotype.Repository;
import ru.egor.model.PetAnimal;
import ru.egor.model.User;

import java.util.List;

public interface UserDAO {

    void add(User user);

    void addPet(int id, PetAnimal petAnimal);

    List<User> list();

    void update(User user);

    void updatePet(int user_id, int pet_id, PetAnimal petAnimal );

    void deleteUser(int id);

    void deletePet(int user_id, int pet_id);

    User getUserById(int id);

    User getUserMobilePhone(String m_phone);

    List<User> searchByFirstName(String first_name);




}
