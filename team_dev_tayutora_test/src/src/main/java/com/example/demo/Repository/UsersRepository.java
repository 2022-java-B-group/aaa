package com.example.demo.Repository;

import com.example.demo.Entity.Users;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findByEmailAndPassword(String email, String password);

    List<Users> findByEmail(String email);

}
