package com.example.userapp.service.impl;

import com.example.userapp.service.*;
import com.example.userapp.dto.*;
import com.example.userapp.entity.*;
import com.example.userapp.repository.*;
import com.example.userapp.mapper.*;
import com.example.userapp.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repo;

	public UserDto createUser(UserDto d) {
		return UserMapper.toDto(repo.save(UserMapper.toEntity(d)));
	}

	public UserDto updateUser(Long id, UserDto d) {
		User u = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		u.setName(d.getName());
		u.setEmail(d.getEmail());
		u.setPhone(d.getPhone());
		return UserMapper.toDto(repo.save(u));
	}

	public UserDto getUserById(Long id) {
		return UserMapper.toDto(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id)));
	}

	public List<UserDto> getAllUsers() {
		return repo.findAll().stream().map(UserMapper::toDto).toList();
	}

	public void deleteUser(Long id) {
		if (!repo.existsById(id))
			throw new ResourceNotFoundException("User", "id", id);
		repo.deleteById(id);
	}
}