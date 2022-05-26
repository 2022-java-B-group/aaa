// package com.example.demo.Controller;

// import java.util.ArrayList;
// import java.util.List;

// import javax.servlet.http.HttpSession;

// import com.example.demo.Entity.Category;
// import com.example.demo.Entity.Rooms;
// import com.example.demo.Repository.CategoryRepository;
// import com.example.demo.Repository.RoomsRepository;
// // import com.example.demo.Repository.UsersRepository;

// // import org.apache.catalina.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.servlet.ModelAndView;

// @Controller
// public class testcontroller {

//     @Autowired
//     RoomsRepository roomsRepository;

//     @Autowired
//     HttpSession session;

//     @Autowired
//     CategoryRepository categoryRepository;



//     // @GetMapping("/hotels/create")
//     // public ModelAndView showform(ModelAndView mv) {
//     //     List<Category> categories = categoryRepository.findAll();
//     //     mv.addObject("categories", categories);
//     //     mv.setViewName("createhotel");
//     //     return mv;
//     // }
//     // @PostMapping("/hotels/create")
//     // public String createhotel(
//     //     @RequestParam(name = "category")Integer category,
//     //     @RequestParam(name = "name")String name,
//     //     @RequestParam(name = "location")String location,
//     //     @RequestParam(name = "rank")Integer rank,
//     //     @RequestParam(name = "amount")Integer amount,
//     //     @RequestParam(name = "price")Integer price,
//     //     Model model
//     // ) {
//     //     if (category.equals(0)) {
//     //         if (name==null || name.length()==0) {
//     //             model.addAttribute("message", "ホテル名を入力してください。");
//     //             System.out.println("this method");
//     //             List<Category> categories = categoryRepository.findAll();
//     //             model.addAttribute("categories", categories);
//     //             return "test";
//     //         }else if (location==null || location.length()==0) {
//     //             model.addAttribute("message", "ホテルの場所を入力してください");
//     //             List<Category> categories = categoryRepository.findAll();
//     //             model.addAttribute("categories", categories);
//     //             return "test";
//     //         }
//     //         Category new_category = new Category(name, location, 10);
//     //         categoryRepository.saveAndFlush(new_category);
//     //         category=categoryRepository.findByNameHotelAndLocation(name, location).get(0).getCode();
//     //     }
        
//     //     Rooms room = new Rooms(category, rank, price);
//     //     roomsRepository.saveAndFlush(room);

//     //     return "redirect:/hotels";
        
//     // }
    
//     // @GetMapping("/hotels")
//     // public ModelAndView showhotels(ModelAndView mv) {
//     //     List<Category> categories = categoryRepository.findAll();
//     //     List<Rooms> rooms = roomsRepository.findAll();
//     //     mv.addObject("categories", categories);
//     //     mv.addObject("rooms", rooms);


//     //     return mv;
        
//     // }


   


// }
