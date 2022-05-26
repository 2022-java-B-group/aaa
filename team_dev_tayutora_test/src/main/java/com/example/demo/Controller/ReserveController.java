package com.example.demo.Controller;

// import java.util.ArrayList;
// import java.util.List;
import java.sql.Date;
// import java.util.Date;

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
// import org.springframework.web.bind.annotation.RequestParam;
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

    
    @GetMapping("/hotels/reserve/{code}")
    public ModelAndView reservelast(
        @PathVariable(name = "code")Integer code,
        ModelAndView mv
    ) {
        Rooms room = roomsRepository.findById(code).get();
        Category category = categoryRepository.findById(room.getCode()).get();
        session.setAttribute("reserve_room", roomsRepository.findById(code).get());
        mv.addObject("category", category);
        mv.addObject("room", room);
        mv.addObject("checkin", (Date)session.getAttribute("checkin"));
        mv.addObject("type", GetRoomType(room.getRoom_rank()));
        // Users user = (Users) session.getAttribute("user");
        // mv.addObject("user", user);
        mv.setViewName("reserve");

        return mv;
    }
    @PostMapping("/reserve/finish")
    public String reserve(Model model) {
        Users user = (Users) session.getAttribute("user");
        Date checkin = (Date) session.getAttribute("checkin");
        long miliseconds = System.currentTimeMillis();
        Date reserve_day = new Date(miliseconds);
        // int num = (int)session.getAttribute("room_num");
        Rooms room = (Rooms)session.getAttribute("reserve_room");
        Reserve reserve = new Reserve(room.getCode(), user.getId(), reserve_day, checkin, room.getPrice());
        session.setAttribute("message", "予約が完了しました！");
        reserveRepository.saveAndFlush(reserve);
        return  "redirect:/hotels";
        
    }
   

    public String GetRoomType(int room_rank) {
        if (room_rank==1) {
            return "松";
        }else if(room_rank==2){
            return "竹";
        }else if(room_rank==3){
            return "梅";
        }else{
            return "";
        }
    }

    // public List<Rooms> EmptyRooms(Date date) {
    //     ArrayList<Rooms> emptyroom = new ArrayList<>();
    //     List<Reserve> reserves =  reserveRepository.findByCheckin(date);
    //     List<Rooms> rooms = roomsRepository.findAll();
    //     System.out.println(reserves);
    //     if (reserves.isEmpty()) {
    //         return rooms;
    //     }else{
    //         for (Reserve reserve : reserves) {
    //             for (Rooms room : rooms) {
    //                 boolean judge = reserve.getCode_room().equals(room.getCode());
    //                 if (judge!=true) {
    //                     emptyroom.add(room);
    //                 }
    //             }
    //         }
    //         return emptyroom;
    //     }
    // }
    // @GetMapping("/reserved/rooms")
    // public String reserved() {
    //     return "test2";
    // }

    // @PostMapping("/reserved/rooms")
    // public ModelAndView reservedRoom(
    //     @RequestParam(name = "date")String date,
    //     ModelAndView mv
    // ) {
    //     Date checkin = Date.valueOf(date);
    //     List<Rooms> reservedRooms = ReservedRooms(checkin);
    //     System.out.println(reservedRooms);
    //     mv.addObject("reservedRooms", reservedRooms);
    //     mv.setViewName("test2");

    //     return mv;
        
    // }


    // public List<Rooms> ReservedRooms(Date date) {
    //     ArrayList<Rooms> reservedroom = new ArrayList<>();
    //     List<Reserve> reserves =  reserveRepository.findByCheckin(date);
    //     List<Rooms> rooms = roomsRepository.findAll();
    //     System.out.println(reserves);


    //     if (reserves.isEmpty()) {
    //         return reservedroom;
    //     }else{
    //         for (Reserve reserve : reserves) {
    //             for (Rooms room : rooms) {
    //                 boolean judge = reserve.getCode_room().equals(room.getCode());
    //                 if (judge) {
    //                     reservedroom.add(room);
    //                 }
    //             }
    //         }
    //         return reservedroom;
    //     }
    // }
    

    
}
