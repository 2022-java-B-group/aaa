package com.example.demo.Repository;

import java.util.List;

import com.example.demo.Entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByNameHotelAndLocation(String name_hotel, String location);

}
