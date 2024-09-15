package com.example.Thawaq.Repository;

import com.example.Thawaq.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
}