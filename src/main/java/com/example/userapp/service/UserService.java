package com.example.userapp.service;

import com.example.userapp.dto.*;
import java.util.*;

public interface UserService {
	UserDto createUser(UserDto d);

	UserDto updateUser(Long id, UserDto d);

	UserDto getUserById(Long id);

	List<UserDto> getAllUsers();

	void deleteUser(Long id);
}