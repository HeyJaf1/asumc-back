package kz.asumc.api.controller;

import kz.asumc.core.service.UserService;
import kz.asumc.dto.LoginRequestDto;
import kz.asumc.dto.LoginResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return userService.login(request);
    }
}
