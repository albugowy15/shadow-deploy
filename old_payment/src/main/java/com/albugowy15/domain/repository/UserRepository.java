package com.albugowy15.domain.repository;

import com.albugowy15.domain.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<UserEntity> {
    @Transactional
    public void create(UserEntity newUser) {
        this.persist(newUser);
    }

    @Transactional
    public Boolean isEmailExists(String email) {
        return this.find("email", email).firstResultOptional().isPresent();
    }

    @Transactional
    public UserEntity findByEmail(String email) {
        return this.find("email", email).firstResult();
    }
}
