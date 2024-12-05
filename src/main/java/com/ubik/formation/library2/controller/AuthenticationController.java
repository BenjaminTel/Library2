package com.ubik.formation.library2.controller;

import com.ubik.formation.library2.converter.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubik.formation.library2.model.User;
import com.ubik.formation.library2.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

	private UserService userService;

    private UserConverter userConverter;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
	
	@Autowired
	public AuthenticationController(UserService userService) {
		this.userService = userService;
        this.userConverter = new UserConverter();
	}
	
    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model, HttpSession session) {
    	try {
            logger.error("Trying to log in as : " + login);

            User user = userService.authenticate(login, password);
            if (user != null) {
                session.setAttribute("user", userConverter.convertEntityToDto(user));
                return "redirect:/";
            } else {
                model.addAttribute("errorMessage", "Invalid username or password !");
                return "login/form";
            }
        } catch (Exception e) {
            logger.error("An error occured : ",e);
            return "error/globalError";
        }
    }

    @GetMapping
    public String showForm(Model model) {
        return "login/form";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
