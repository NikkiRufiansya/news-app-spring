package com.nikki.webnews.controller.auth;


import com.nikki.webnews.dto.UserDto;
import com.nikki.webnews.model.Device;
import com.nikki.webnews.model.User;
import com.nikki.webnews.model.Visitor;
import com.nikki.webnews.repository.DeviceRepository;
import com.nikki.webnews.service.NewsService;
import com.nikki.webnews.service.UserService;
import com.nikki.webnews.service.VisitorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private NewsService newsService;
    private VisitorService visitorService;

    private DeviceRepository deviceRepository;


    public AuthController(UserService userService, NewsService newsService, VisitorService visitorService, DeviceRepository deviceRepository) {
        this.userService = userService;
        this.newsService = newsService;
        this.visitorService = visitorService;
        this.deviceRepository = deviceRepository;

    }

    @GetMapping("/")
    public String home(Model model) {
        int newsCount = newsService.getTotalNews();
        int deviceCount = deviceRepository.getTotalDevice();

        List<Visitor> visitorData = visitorService.getVisitorData();
        List<Device> deviceList = deviceRepository.findAll();
        model.addAttribute("totalNews", newsCount);
        model.addAttribute("totalDevice", "");
        model.addAttribute("visitorData", visitorData);
        model.addAttribute("deviceCount", deviceCount);
        model.addAttribute("deviceList", deviceList);
        return "index";
    }


    // handler method to handle login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("register")
    public String showRegisterForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("register/save")
    public String register(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
        User exitingUser = userService.findUserByEmail(userDto.getEmail());

        if (exitingUser != null && exitingUser.getEmail() != null && !exitingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }


}
