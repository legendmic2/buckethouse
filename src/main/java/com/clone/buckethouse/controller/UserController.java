package com.clone.buckethouse.controller;


import com.clone.buckethouse.dto.ResponseDTO;
import com.clone.buckethouse.dto.UserDTO;
import com.clone.buckethouse.model.UserEntity;
import com.clone.buckethouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        //User 등록을 위한 메서드

        try {
            //RequestBody로 받은 DTO를 Entity로 변환
            UserEntity user = UserEntity.builder()
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .email(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .build();

            //변환된 Entity를 Service 이용하여 repository로 전달
            UserEntity registeredUser = service.create(user);


            //변환된 DTO를 다시 Entity로 변환
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .email(registeredUser.getEmail())
                    .password(registeredUser.getPassword())
                    .build();

            return ResponseEntity.ok().body(responseUserDTO);

        }catch(Exception e){
            ResponseDTO response = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO){

        UserEntity user = service.getByCredentials(userDTO.getEmail(),userDTO.getPassword());

        if(user != null){
            //해당 User info가 있다는 것.
           final UserDTO responseUserDTO = UserDTO.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .build();

            return ResponseEntity.ok().body(responseUserDTO);


        }else{
            ResponseDTO response = ResponseDTO.builder().error("login failed").build();
            return ResponseEntity.badRequest().body(response);

        }

    }
}
