package com.example.demo.user.service;

import com.example.demo.user.entity.Address;
import com.example.demo.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    private final EntityManager entityManager;

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User findUserByFullName(String firstName, String lastName) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserByPhone(String phone) {
        return null;
    }

    @Override
    public User findUserByParameters(Map<String, Object> parameters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        parameters.forEach((key, value) -> predicates.add(cb.equal(root.get(key), value)));

        query.select(root).where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void changeUserFullName(String firstName, String lastName) {

    }

    @Override
    public void changeUserEmail(String email) {

    }

    @Override
    public void changeUserPhone(String phone) {

    }

    @Override
    public void changeUserAddress(Address address) {

    }

    @Override
    public void changeUserPaymentMethod(String method) {

    }
}
