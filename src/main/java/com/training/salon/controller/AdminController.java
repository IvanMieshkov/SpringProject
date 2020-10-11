package com.training.salon.controller;

import com.training.salon.entity.Role;
import com.training.salon.entity.User;
import com.training.salon.service.MasterService;
import com.training.salon.service.UserService;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final MasterService masterService;
    private final MessageSource messageSource;

    public AdminController(UserService userService, MasterService masterService, MessageSource messageSource) {
        this.userService = userService;
        this.masterService = masterService;
        this.messageSource = messageSource;
    }

    @GetMapping("/userlist")
    public String userList(Model model) {

        model.addAttribute("users", userService.findAllUsers());
        return "/admin/userlist";
    }

    @GetMapping("/edituser/{user}")
    public String userEditForm(@PathVariable User user,
                               Locale locale,
                               Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "/admin/useredit";
    }

    @PostMapping("/save")
    public String userUpdate(@RequestParam String role,
                             @RequestParam("userId") User user) {
        userService.updateRole(role, user);

        return "redirect:/admin/userlist";
    }

}
