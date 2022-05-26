package com.example.demo.Entity;

import java.util.Date;

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
@Table(name = "reserve")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer code;

    @Column(name = "code_room")
    private Integer code_room;
    @Column(name = "id_user")
    private Integer iduser;

    @Column(name = "reserve_day")
    private Date reserve_day;

    @Column(name = "checkin")
    private Date checkin;

    @Column(name = "price")
    private Integer price;

    public Reserve(Integer code_room, Integer id_user, Date reserve_day, Date checkin, Integer price) {

        super();
        this.code_room = code_room;
        this.iduser = id_user;
        this.reserve_day = reserve_day;
        this.checkin = checkin;
        this.price = price;
    }

    public Integer getId_user() {
        return iduser;
    }

}
