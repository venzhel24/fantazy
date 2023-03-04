package ru.sfedu.fantazy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sfedu.fantazy.model.User;
import ru.sfedu.fantazy.service.SecurityService;
import ru.sfedu.fantazy.service.UserService;
import ru.sfedu.fantazy.service.UserServiceImpl;
import ru.sfedu.fantazy.util.UserValidator;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserServiceImpl userService;

    private final SecurityService securityService;

    private final UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(required = false) String error) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        return "login";
    }

    @GetMapping("/")
    public String welcome() {
        return "index";
    }
}
