package com.example.demo.Repository;

import com.example.demo.Entity.Reserve;
import java.util.List;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer>{
    List<Reserve> findByCheckin(Date checkin);
    
}
