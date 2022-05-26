package com.example.demo.Controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UsersRepository;



@Controller

public class UsersController {
	@Autowired
	HttpSession session;
	
	@Autowired
	UsersRepository usersRepository;
	

	//新規会員登録の表示
	@RequestMapping("/users/new")
	public ModelAndView showAdd(ModelAndView mv) {
		mv.setViewName("users_new");
		return mv;
	}
	
	//formを受け取り新規登録
	@RequestMapping(value="/user/create", method=RequestMethod.POST)
	public  String create_user(
			@RequestParam("name")String name,
			@RequestParam("address")String address,
			@RequestParam("tel")String tel,
			@RequestParam("email")String email,
			@RequestParam("birthday")Date birthday,
			@RequestParam("sex")Integer sex,
			@RequestParam("password")String password,
			// @RequestParam("admin")Integer admin,
			Model model
			)	{
		Users user =new Users(name, address,tel,email, birthday,sex ,password);
		usersRepository.saveAndFlush(user);
		session.setAttribute("message", "登録が完了しました！");
		
		return "redirect:/hotels";
	}
	
	
	
	
	
	//管理者によるアカウントの表示
	@RequestMapping("/users")
	public ModelAndView users(ModelAndView mv) {
		List<Users> usersList= usersRepository.findAll();
		mv.addObject("users",usersList);
		mv.setViewName("users");
		
		return mv;
	}
	
	// 更新ボタン押下(管理者)管理者にするか否か
	@RequestMapping(value = "/users_index/users/{id}/update", method = RequestMethod.POST)
	public String updateUserByAdimn(
			@PathVariable("id")Integer id,
			@RequestParam("admin")Integer admin,
			ModelAndView mv
	) {

		// 更新処理
		Users user = usersRepository.findById(id).get();
		// Users updateuser = new Users(id,user.getName_user(),user.getAddress(),user.getTel(),user.getEmail(),user.getPassword(),user.getBirthday(),user.getSex(),admin);
		Users updateuser = new Users(id, user.getName_user(), user.getAddress()	, user.getTel(), user.getEmail(), user.getBirthday(), user.getSex(), user.getPassword(), admin);
		usersRepository.saveAndFlush(updateuser);
		// リダイレクト
		return "redirect:/users";
	}
	

	// 削除ボタン押下(管理者)
	@RequestMapping(value = "/users_index/users/{id}/delete", method = RequestMethod.POST)
	public String deleteUserByAdmin(@PathVariable("id") int id) {
		// 削除処理
		usersRepository.deleteById(id);
		// リダイレクト
		return "redirect:/users";
	}

	// 画面表示（ユーザ情報）
	@GetMapping("/user_update2")
	public ModelAndView show(ModelAndView mv) {
		
		// ログイン情報取得
		Users login = (Users)session.getAttribute("user");
		mv.addObject("user", login);
		mv.setViewName("update");
		return mv;
	}
	
	// 更新
	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public String update(
		
			@RequestParam("name_user") String name_user,
			@RequestParam("address") String address, 
			@RequestParam("tel") String tel,
			@RequestParam("email") String email, 
			@RequestParam("password") String password,
			@RequestParam("birthday") String date,
			@RequestParam("sex") Integer sex)
		{
			
		System.out.println(name_user);
		Date birthday = Date.valueOf(date);
		Users user = (Users)session.getAttribute("user");
			
			// 更新処理
		// Users updateuser = new Users(user.getId(),name_user,address,tel, email,birthday,sex,password,user.getAdmin());
		Users updateuser = new Users(user.getId(), name_user, address, tel, email, birthday, sex, password, user.getAdmin());
		usersRepository.saveAndFlush(updateuser);
		session.setAttribute("user", updateuser);
		session.setAttribute("message", "ユーザ情報の更新をしました！");

		return "redirect:/hotels";
	}

	// 削除ボタン押下
	@GetMapping(value = "/delete")
	public String deleteByUser() {

		Users user = (Users)session.getAttribute("user");	
		// 削除処理
		usersRepository.deleteById(user.getId());
		
		return "finish";
	}
}
