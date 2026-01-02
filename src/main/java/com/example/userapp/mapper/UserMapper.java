package com.example.userapp.mapper;

import com.example.userapp.dto.*;
import com.example.userapp.entity.*;

public class UserMapper {
	
	public static UserDto toDto(User u) {
		return new UserDto(u.getId(), u.getName(), u.getEmail(), u.getPhone());
	}

	public static User toEntity(UserDto u) {
		return new User(u.getId(), u.getName(), u.getEmail(), u.getPhone());
	}
}