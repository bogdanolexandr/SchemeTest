package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.User;
import com.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userForm",new User());
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(@ModelAttribute("userForm") User userForm, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "registration";
		}
		if(!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
			model.addAttribute("passwordError","пароли не совпадают");
			return "registration";
		}
		if(!userService.saveUser(userForm)) {
			model.addAttribute("usernameError","Пользователь существует");
			return "registration";
		}
		return "redirect:/";
	}
	
	
	
}
