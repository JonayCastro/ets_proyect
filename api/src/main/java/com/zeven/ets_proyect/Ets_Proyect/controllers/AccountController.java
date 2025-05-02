package com.zeven.ets_proyect.Ets_Proyect.controllers;


import com.zeven.ets_proyect.Ets_Proyect.config.ApiMessage;
import com.zeven.ets_proyect.Ets_Proyect.config.ApiPaths;
import com.zeven.ets_proyect.Ets_Proyect.dto.UserDTO;
import com.zeven.ets_proyect.Ets_Proyect.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = ApiPaths.ACCOUNT_PATH)
public class AccountController {

    private final UserService userService;

    AccountController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(path = ApiPaths.CREATE_PATH, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        try {
            String registerBotUrl = this.userService.createUser(userDTO);
            return ResponseEntity.ok(Map.of("url", registerBotUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(path = ApiPaths.LOGIN_PATH)
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            String token = userService.login(userDTO);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiMessage.ACCESS_DENIED);
        }
    }
}
