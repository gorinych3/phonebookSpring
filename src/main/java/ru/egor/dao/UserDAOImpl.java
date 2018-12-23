package ru.egor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.egor.model.PetAnimal;
import ru.egor.model.User;
import ru.egor.model.UsersPet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
        //Query query = sessionFactory.getCurrentSession().createQuery("select user_id from User where ")

    }

    @Override
    public void addPet(int id, PetAnimal petAnimal) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class,id);
            Set<UsersPet> usersPets = user.getUsersPets();
            UsersPet up = new UsersPet();
            up.setUser(user);
            up.setPetAnimal(petAnimal);
            usersPets.add(up);
            user.setUsersPets(usersPets);
        sessionFactory.getCurrentSession().update(user);
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//
            //поиск юзера по id и добавление в него животных
//            User user = session.get(User.class,id);
//            Set<UsersPet> usersPets = user.getUsersPets();
//            UsersPet up = new UsersPet();
//            up.setUser(user);
//            up.setPetAnimal(petAnimal);
//            usersPets.add(up);
//            user.setUsersPets(usersPets);
//            session.update(user);
//
//            transaction.commit();
//
//            System.out.println("add user");
//            session.close();
//        }finally {
//            if(sessionFactory!=null){
//                sessionFactory.close();
//            }
//        }
    }



    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> list() {
        System.out.println("Запуск метода selectAll в UserDAOImpl");
        if(sessionFactory==null){
            System.out.println("problem with sessionFactory");
        }else System.out.println("ok");
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
        if(users!=null||users.size()!=0){
            System.out.println("Проблемы в хибернейте нет - лист не пустой");
        } else System.out.println("Проблема в хибернейте - лист пустой");
        return users;
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void updatePet( int user_id, int pet_id, PetAnimal petAnimal) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class,user_id);
            Set<UsersPet> usersPets = user.getUsersPets();

            Iterator<UsersPet> userIterator = usersPets.iterator();
            while(userIterator.hasNext()) {
                UsersPet nextUserPet = userIterator.next();
                if (nextUserPet.getPetAnimal().getPet_id()==pet_id) {
                    nextUserPet.getPetAnimal().setPetName(petAnimal.getPetName());
                    nextUserPet.getPetAnimal().setPetType(petAnimal.getPetType());
                }
            }

            user.setUsersPets(usersPets);
            sessionFactory.getCurrentSession().update(user);
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//
//            //поиск юзера по id и update у него животного по pet_id
//
//            User user = session.get(User.class,user_id);
//            Set<UsersPet> usersPets = user.getUsersPets();
//
//            Iterator<UsersPet> userIterator = usersPets.iterator();
//            while(userIterator.hasNext()) {
//                UsersPet nextUserPet = userIterator.next();
//                if (nextUserPet.getPetAnimal().getPet_id()==pet_id) {
//                    nextUserPet.getPetAnimal().setPetName(petAnimal.getPetName());
//                    nextUserPet.getPetAnimal().setPetType(petAnimal.getPetType());
//                }
//            }
//
//            user.setUsersPets(usersPets);
//            session.update(user);
//
//            transaction.commit();
//
//            System.out.println("update pets");
//            session.close();
//
//        }finally {
//            if(sessionFactory!=null){
//                sessionFactory.close();
//            }
//        }
    }

    public void deleteUser(int id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class,id);
        sessionFactory.getCurrentSession().delete(user);
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//
//            User user = session.get(User.class,id);
//            session.delete(user);
//
//            transaction.commit();
//
//            System.out.println("delete user");
//            session.close();
//        }finally {
//            if(sessionFactory!=null){
//                sessionFactory.close();
//            }
//        }
    }
    @Override
    @SuppressWarnings("unchecked")
    public void deletePet(int user_id, int pet_id) {
        User user = (User) sessionFactory.getCurrentSession().get(User.class,user_id);
        PetAnimal pa = (PetAnimal) sessionFactory.getCurrentSession().get(PetAnimal.class,pet_id);
        Query <UsersPet> query = sessionFactory.getCurrentSession().createQuery("delete UsersPet where pet_id = :param1 and user_id = :param2");
            query.setParameter("param1",pet_id);
            query.setParameter("param2",user_id);
            query.executeUpdate();
//        try {
//            Session session = sessionFactory.openSession();
//            Transaction transaction = session.beginTransaction();
//
//            User user = session.get(User.class,user_id);
//            PetAnimal pa = session.get(PetAnimal.class,pet_id);
//
//            Query <UsersPet> query = session.createQuery("delete UsersPet where pet_id = :param1 and user_id = :param2");
//            query.setParameter("param1",pet_id);
//            query.setParameter("param2",user_id);
//            query.executeUpdate();
//
//            transaction.commit();
//
//            System.out.println("delete pet");
//            session.close();
//
//        }finally {
//            if(sessionFactory!=null){
//                sessionFactory.close();
//            }
//        }
    }

    @Override
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class,id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserMobilePhone(String m_phone) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User where m_phone = :paramName");
        query.setParameter("paramName",m_phone);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchByFirstName(String first_name){
        Query<User> query = sessionFactory.getCurrentSession().createQuery("FROM User where f_name = :paramName");
        query.setParameter("paramName",first_name);
        return query.list();
    }
}
