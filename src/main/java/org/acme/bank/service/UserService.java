package org.acme.bank.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.bank.model.User;
import org.acme.bank.rest.dto.CreateUserRequest;

@ApplicationScoped
public class UserService {

    public User createUser(CreateUserRequest CreateUserRequest) {
        User user = new User();
        user.setName(CreateUserRequest.getName());
        user.setAge(CreateUserRequest.getAge());
        user.setPhone(CreateUserRequest.getPhone());
        user.setAddress(CreateUserRequest.getAddress());

        user.persist();
        String successMessage = "Usuário criado com sucesso! " + toString();
        System.out.println(successMessage);
        return user;
    }
}

