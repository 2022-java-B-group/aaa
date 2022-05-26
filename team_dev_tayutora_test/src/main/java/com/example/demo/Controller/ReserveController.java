package com.example.demo.Controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Reserve;
import com.example.demo.Entity.Rooms;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ReserveRepository;
import com.example.demo.Repository.RoomsRepository;
import com.example.demo.Repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReserveController {

    @Autowired
    HttpSession session;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    // 部屋番号より予約をする
    @GetMapping("/hotels/reserve/{code}")
    public ModelAndView reservelast(
            @PathVariable(name = "code") Integer code,
            ModelAndView mv) {
        Rooms room = roomsRepository.findById(code).get();
        Category category = categoryRepository.findById(room.getCode()).get();
        session.setAttribute("reserve_room", roomsRepository.findById(code).get());
        mv.addObject("category", category);
        mv.addObject("room", room);
        mv.addObject("checkin", (Date) session.getAttribute("checkin"));
        mv.addObject("type", GetRoomType(room.getRoom_rank()));
        mv.setViewName("reserve");

        return mv;
    }

    // 予約確認画面
    @PostMapping("/reserve/finish")
    public String reserve(Model model) {
        Users user = (Users) session.getAttribute("user");
        Date checkin = (Date) session.getAttribute("checkin");
        long miliseconds = System.currentTimeMillis();
        Date reserve_day = new Date(miliseconds);
        Rooms room = (Rooms) session.getAttribute("reserve_room");
        Reserve reserve = new Reserve(room.getCode(), user.getId(), reserve_day, checkin, room.getPrice());
        session.setAttribute("message", "予約が完了しました！");
        reserveRepository.saveAndFlush(reserve);
        return "redirect:/hotels";

    }

    // 予約の削除
    @GetMapping("/delete/reserve/{code}")
    public String deleteReserve(
            @PathVariable(name = "code") Integer code) {
        reserveRepository.deleteById(code);
        return "redirect:/user/reserves";
    }

    // 部屋番号より部屋のランクを返す
    public String GetRoomType(int room_rank) {
        if (room_rank == 1) {
            return "松";
        } else if (room_rank == 2) {
            return "竹";
        } else if (room_rank == 3) {
            return "梅";
        } else {
            return "";
        }
    }

}
