package com.example.userapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.userapp.entity.User;
public interface UserRepository extends JpaRepository<User,Long>{}