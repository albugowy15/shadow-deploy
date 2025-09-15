package com.albugowy15.repositories;

import com.albugowy15.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    @Transactional
    public void create(UserEntity newUser) {
        this.persist(newUser);
    }
}
