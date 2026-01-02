package com.example.userapp.controller;

import com.example.userapp.dto.*;
import com.example.userapp.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private  UserService service;

	@PostMapping
	public UserDto create(@RequestBody UserDto d) {
		return service.createUser(d);
	}

	@GetMapping("/{id}")
	public UserDto get(@PathVariable Long id) {
		return service.getUserById(id);
	}

	@GetMapping
	public List<UserDto> all() {
		return service.getAllUsers();
	}

	@PutMapping("/{id}")
	public UserDto update(@PathVariable Long id, @RequestBody UserDto d) {
		return service.updateUser(id, d);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		service.deleteUser(id);
		return "User deleted";
	}
}