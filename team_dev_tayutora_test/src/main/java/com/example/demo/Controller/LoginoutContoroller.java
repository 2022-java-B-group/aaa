package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UsersRepository;

@Controller
public class LoginoutContoroller {
	@Autowired
	HttpSession session;

	@Autowired
	UsersRepository usersRepository;

	// ログイン画面の表示
	@RequestMapping(value = "/login")
	public String login() {
		return "login";

	}

	// ログインを実行
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {

		// 名前が空の場合にエラーとする
		if (email == null || email.length() == 0) {
			model.addAttribute("message", "Emailを入力してください");
			return "login";
		}
		// パスワードが空の場合にエラーとする
		if (password == null || password.length() == 0) {
			model.addAttribute("message", "パスワードを入力してください");
			return "login";
		}

		// Users user = usersRepository.findByEmailAndPassword(email, password).get(0);
		List<Users> users = usersRepository.findByEmailAndPassword(email, password);

		if (users.isEmpty()) {

			List<Users> emailList = usersRepository.findByEmail(email);
			if (emailList.isEmpty()) {
				model.addAttribute("message", "アカウント情報がありません");
				model.addAttribute("judge", "1");
				return "login";

			}

			model.addAttribute("message", "パスワードが違います");
			return "login";

		}

		session.setAttribute("user", users.get(0));
		session.setAttribute("name", users.get(0).getName_user());
		session.setAttribute("message", "ログインしました！");
		return "redirect:/hotels";

	}

	// ログアウトを実行
	@RequestMapping("/logout")
	public String logout() {
		// ログイン画面表示処理を実行するだけ
		session.removeAttribute("user");
		session.setAttribute("message", "ログアウトされました。");
		return "redirect:/hotels";

	}

}
