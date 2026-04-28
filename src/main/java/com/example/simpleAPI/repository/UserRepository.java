package com.example.simpleAPI.repository;

import com.example.simpleAPI.models.Note;
import com.example.simpleAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findUserById(Long userId);

}
