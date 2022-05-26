package com.example.demo.Controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Reserve;
import com.example.demo.Entity.Rooms;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ReserveRepository;
import com.example.demo.Repository.RoomsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoomsController {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RoomsRepository roomsRepository;

    @Autowired
    ReserveRepository reserveRepository;

    @Autowired
    HttpSession session;

    //ホテルの一覧表示
    @GetMapping("/hotels")
    public ModelAndView showHotels(
        ModelAndView mv
    ) {
       
        Users user = (Users)session.getAttribute("user");
        if (user==null) {
            user = new Users(0);
            session.setAttribute("user", user);
        }
        System.out.println(user);
        session.setAttribute("categories", categoryRepository.findAll());
        List<Rooms> rooms = roomsRepository.findAll();
        mv.addObject("rooms", rooms); 
        mv.setViewName("top");    

        return mv;
    }

    //泊まりたい日を選択して空いている日の部屋を表示
    @PostMapping("/hotels")
    public ModelAndView showbyDate(
        @RequestParam(name = "date")String date,
        ModelAndView mv
    ) {
        Date checkin = Date.valueOf(date);
        session.setAttribute("checkin", checkin);
        List<Rooms> rooms = EmptyRooms(checkin);
        mv.addObject("date", date);
        mv.addObject("rooms", rooms);
        mv.setViewName("top");
        return mv;
    
    }
    //ホテルにより検索
    @PostMapping("/location")
    public ModelAndView showbyLocation(
        @RequestParam(name = "category")Integer category,
        ModelAndView mv
    ) {
        List<Rooms> rooms = new ArrayList<Rooms>();
        if (category==0) {
            rooms = roomsRepository.findAll();
        }else{
            rooms = roomsRepository.findByCodeCategory(category);
        }
        session.removeAttribute("message");
        mv.addObject("rooms", rooms);
        mv.setViewName("top");
        return mv;
        
    }

    //ホテルを新しく作るformの表示
    @GetMapping("/hotels/create")
    public ModelAndView showform(ModelAndView mv) {
        List<Category> categories = categoryRepository.findAll();
        mv.addObject("categories", categories);
        mv.setViewName("createhotel");
        return mv;
    }

    //formより情報を受け取りホテルの作成
    @PostMapping("/hotels/create")
    public String createhotel(
        @RequestParam(name = "category")Integer category,
        @RequestParam(name = "name")String name,
        @RequestParam(name = "location")String location,
        @RequestParam(name = "rank")Integer rank,
        @RequestParam(name = "amount")Integer amount,
        @RequestParam(name = "price")Integer price,
        Model model
    ) {
        if (category.equals(0)) {
            if (name==null || name.length()==0) {
                model.addAttribute("message", "ホテル名を入力してください。");
                System.out.println("this method");
                List<Category> categories = categoryRepository.findAll();
                model.addAttribute("categories", categories);
                return "test";
            }else if (location==null || location.length()==0) {
                model.addAttribute("message", "ホテルの場所を入力してください");
                List<Category> categories = categoryRepository.findAll();
                model.addAttribute("categories", categories);
                return "test";
            }
            Category new_category = new Category(name, location, 10);
            categoryRepository.saveAndFlush(new_category);
            category=categoryRepository.findByNameHotelAndLocation(name, location).get(0).getCode();
        }else{
            name = categoryRepository.findById(category).get().getName_hotel();
            location =categoryRepository.findById(category).get().getLocation();
        }
        
        Rooms room = new Rooms(category,name, location, rank, price);
        roomsRepository.saveAndFlush(room);

        return "redirect:/hotels";
        
    }
    // 予約状況の確認
    @GetMapping("/reserved/rooms")
    public ModelAndView reserved(ModelAndView mv) {
        long miliseconds = System.currentTimeMillis();
        Date today = new Date(miliseconds);
        List<Rooms> reservedrooms = ReservedRooms(today);
        if (reservedrooms.isEmpty()) {
            mv.addObject("message", "予約はありません");
        }else{
            mv.addObject("reservedRooms", reservedrooms);
        }
        // mv.addObject("reservedRooms", reservedrooms);
        mv.addObject("day", today);
        mv.setViewName("reserved");

        return mv;
       
    }
    //日付より予約状況を出力
    @PostMapping("/reserved/rooms")
    public ModelAndView reservedRoom(
        @RequestParam(name = "date")String date,
        ModelAndView mv
    ) {
        Date checkin = Date.valueOf(date);
        List<Rooms> reservedrooms = ReservedRooms(checkin);
        if (reservedrooms.isEmpty()) {
            mv.addObject("message", "予約はありません");
        }else{
            mv.addObject("reservedRooms", reservedrooms);
        }
        mv.addObject("day", checkin);
        mv.setViewName("reserved");

        return mv;
        
    }

    // 予約のある部屋を返す
    public List<Rooms> ReservedRooms(Date date) {
        ArrayList<Rooms> reservedroom = new ArrayList<>();
        List<Reserve> reserves =  reserveRepository.findByCheckin(date);
        List<Rooms> rooms = roomsRepository.findAll();
        System.out.println(reserves);
        if (reserves.isEmpty()) {
            return reservedroom;
        }else{
            for (Reserve reserve : reserves) {
                for (Rooms room : rooms) {
                    boolean judge = reserve.getCode_room().equals(room.getCode());
                    if (judge) {
                        reservedroom.add(room);
                    }
                }
            }
            return reservedroom;
        }
    }

    //予約のない部屋を返す。 
    public List<Rooms> EmptyRooms(Date date) {
        List<Rooms> reservedrooms = ReservedRooms(date);
        List<Rooms> rooms = roomsRepository.findAll();

        for (int i = 0; i < rooms.size(); i++) {
            for (Rooms room2 : reservedrooms) {
                boolean judge = rooms.get(i).getCode().equals(room2.getCode());
                if (judge) {
                    rooms.remove(i);
                }   
            }
        }

        return rooms;
    }
    
}
