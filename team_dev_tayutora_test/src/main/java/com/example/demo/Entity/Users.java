package com.example.demo.Entity;

import java.sql.Date;

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
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name_user")
    private String name_user;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;
    @Column(name = "email")
    private String email;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "password")
    private String password;
    @Column(name = "admin")
    private Integer admin;

    
    public  Users(String name_user, String address, String tel,String email,Date birthday,Integer sex ,String password) {
        super();
        this.name_user=name_user;
        this.address=address;
        this.tel=tel;
        this.email=email;
        this.birthday=birthday;
        this.sex=sex;
        this.password=password;
        this.admin=0;
    }
    // public  Users(int id,String name_user, String address, String tel,String email,String password,Date birthday,Integer sex,Integer admin) {
    //     this(name_user, address,  tel,email, birthday, sex, password);
    //     this.id = id;
    //     this.admin=admin;
    // }

    public  Users(int admin) {
        super();
        this.admin=admin;
        
    }
    
}
