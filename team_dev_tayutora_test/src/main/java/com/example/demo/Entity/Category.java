package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(name = "name_hotel")
    private String nameHotel;

    @Column(name = "location")
    private String location;

    @Column(name = "room_number")
    private Integer room_number;

    public Category(String name_hotel, String location, Integer room_number) {
        super();
        this.nameHotel = name_hotel;
        this.location = location;
        this.room_number = room_number;

    }

    public String getName_hotel() {
        return nameHotel;
    }

}
