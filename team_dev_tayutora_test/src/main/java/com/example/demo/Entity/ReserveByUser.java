package com.example.demo.Entity;

import java.util.Date;

import com.example.demo.Repository.ReserveRepository;
import com.example.demo.Repository.RoomsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Service
public class ReserveByUser {
    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    RoomsRepository roomsRepository;

    // reserverepositoryより予約番号を取得し格納する。
    public Constlacuter newuser(Integer code) {
        Reserve reserve = reserveRepository.findById(code).get();
        Rooms room = roomsRepository.findById(reserve.getCode_room()).get();
        Constlacuter cons = new Constlacuter(reserve, room, code);

        return cons;
    }

}

@Data
class Constlacuter {

    private Integer code;
    private String name;
    private String location;
    private Integer type;
    private Date reserveDay;
    private Date checkinDay;

    public Constlacuter(Reserve reserve, Rooms room, Integer code) {
        this.code = code;
        this.name = room.getName_hotel();
        this.location = room.getLocation();
        this.type = room.getRoom_rank();
        this.reserveDay = reserve.getReserve_day();
        this.checkinDay = reserve.getReserve_day();

    }

}
