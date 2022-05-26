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
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;
    @Column(name = "code_category")
    private Integer codeCategory;

    @Column(name = "name_hotel")
    private String name_hotel;

    @Column(name = "location")
    private String location;
    
    @Column(name = "room_rank")
    private Integer room_rank;

    @Column(name = "price")
    private Integer price;

    public  Rooms(Integer code_category,String name_hotel, String location,  Integer room_rank, Integer price) {
        super();
        this.codeCategory=code_category;
        this.name_hotel=name_hotel;
        this.location=location;
        this.room_rank=room_rank;
        this.price=price;
    }

    public String getCode_category() {
        return codeCategory.toString();
    }
    
}
