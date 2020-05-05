package com.upgrad.technical.api.controller;

import com.upgrad.technical.service.business.PasswordCryptographyProvider;
import com.upgrad.technical.service.business.SignupBusinessService;
import com.upgrad.technical.service.entity.UserEntity;

import sun.net.www.content.text.plain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class SignupController {

    @Autowired
    private SignupBusinessService signupBusinessService;
    
  //  @Autowired
    //private PasswordCryptographyProvider passwordCryptographyProvider;

   
    @RequestMapping(method = RequestMethod.POST, path = "/usersignup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserEntity> userSignup(@RequestBody  UserEntity signupUserRequest) {

        final UserEntity userEntity = new UserEntity();

        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setFirstName(signupUserRequest.getFirstName());
        userEntity.setLastName(signupUserRequest.getLastName());
        userEntity.setEmail(signupUserRequest.getEmail());
   //   String password =  passwordCryptographyProvider.encrypt(signupUserRequest.getPassword(),"1234abc");
        
        userEntity.setPassword(signupUserRequest.getPassword());
        userEntity.setMobilePhone(signupUserRequest.getMobilePhone());
        userEntity.setSalt("1234abc");
        userEntity.setRole("nonadmin");

        final UserEntity createdUserEntity = signupBusinessService.signup(userEntity);
        createdUserEntity.setSalt("USER SUCCESSFULLY REGISTERED");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserEntity);
    }
}
