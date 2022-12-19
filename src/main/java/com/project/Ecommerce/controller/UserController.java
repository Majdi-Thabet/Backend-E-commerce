package com.project.EcommerceProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Ecommerce.service.UserService;



@RestController
@RequestMapping("/user")
public class 	UserController {

	@Autowired private UserService userService;

	@GetMapping("/")
	public ResponseEntity<List<UserService>> getUsers() {
		List<UserService> dtos = userService.listProfiles();
		return new ResponseEntity<List<UserService>>(dtos, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addSurvey(@RequestBody @Valid UserService profile) {
		userService.addProfile(profile);
		return new ResponseEntity<>(new ApiResponse(true, "Profile has been created."), HttpStatus.CREATED);
	}	
}