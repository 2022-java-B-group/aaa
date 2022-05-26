package com.example.demo.Repository;

import com.example.demo.Entity.Rooms;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Integer>{
    List<Rooms> findByCodeCategory(Integer code_category);
}
