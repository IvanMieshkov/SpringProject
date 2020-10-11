package com.training.salon.controller;

import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.repository.UserRepository;
import com.training.salon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class RegistrationController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserRepository userRepo;

    private final UserService userService;
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model, Locale locale) {
        User userFromDbWithEmail = userRepo.findByEmail(user.getEmail());
        if (userFromDbWithEmail != null) {
            model.addAttribute("emailError", messageSource.getMessage("not.unique.email", null, locale));
            return "registration";
        }
        User userFromDbWithUsername = userRepo.findByUsername(user.getUsername());
        if(userFromDbWithUsername != null) {
          model.addAttribute("usernameError", messageSource.getMessage("not.unique.username", null, locale));
          return "registration";
        }

        if(userRepo.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }
        userService.saveNewUser(user);
        return "redirect:/login";
    }
}
