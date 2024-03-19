package com.tommy.capstone_perscholas.controller;

import com.tommy.capstone_perscholas.Dto.UserDTO;
import com.tommy.capstone_perscholas.Service.UserLoginServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class UserLoginController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    private UserLoginServiceImpl userDetailsService;

    @Autowired
    private UserLoginController(UserLoginServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    private String redirectToLogin(){
        return "webpage/MainPage";
    }

    @GetMapping("/sign-up")
    public String signUp(Model model){
        model.addAttribute("userDto", new UserDTO());
        return "user/sign-up";
    }

    @PostMapping("/signup-process")
    public String signupProcess(@Valid @ModelAttribute("userDto") UserDTO userDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            log.warn("Wrong attempt");
            return "user/sign-up";
        }
        if(userDetailsService.existsByUsername(userDto.getUserName())) {
            bindingResult.rejectValue("userName", "error.user", "Username is already taken");
            model.addAttribute("error", "Username is already taken");
            log.warn("Wrong attempt");
            return "user/sign-up";
        }

        userDetailsService.creat(userDto);
        return "user/confirmation";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        log.info("Login page displayed");
        return "user/new";
    }

    @RequestMapping("/userHome")
    public String getHome(){
        log.info("Home page displayed");
        return "user/userHome";
    }

    @RequestMapping("/Portfolio")
    public String getPortfolio(){
        log.info("Portfolio page displayed");
        return "webpage/Portfolio";
    }

    @RequestMapping("/Service")
    public String getService(){
        log.info("Service page displayed");
        return "webpage/Service";
    }

    @RequestMapping("/AboutMe")
    public String getAboutMe(){
        log.info("About me page displayed");
        return "webpage/AboutMe";
    }

    @RequestMapping("/MainPage")
    public String getMainPage(){
        log.info("Main page displayed");
        return "webpage/MainPage";
    }

}
