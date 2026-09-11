package com.merkit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.UserResponse;
import com.merkit.entity.User;
import com.merkit.exception.UserNotFoundException;
import com.merkit.mapper.UserMapper;
import com.merkit.repo.UserRepository;
import com.merkit.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private UserMapper userMapper;

	@Autowired
	UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserResponse getUserById(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		return userMapper.toResponse(user);
	}

	@Override
	public UserResponse getUserByUsername(String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return userMapper.toResponse(user);
	}

	@Override
	public List<UserResponse> getAllUsers() {

		List<User> users = userRepository.findAll();

		return users.stream().map(userMapper::toResponse).toList();
	}

	@Override
	public ApiResponse enableUser(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		user.setEnabled(true);
		userRepository.save(user);

		return ApiResponse.builder().success(true).message("User enabled successfully.").build();
	}

	@Override
	public ApiResponse disableUser(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		user.setEnabled(false);
		userRepository.save(user);

		return ApiResponse.builder().success(true).message("User disabled successfully.").build();
	}

	@Override
	public ApiResponse deleteUser(Long id) {

		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

		userRepository.delete(user);

		return ApiResponse.builder().success(true).message("User deleted successfully.").build();
	}

	@Override
	public Boolean getUserNameExist(String username) {
		return userRepository.findByUsername(username).isPresent();
			
	}

	@Override
	public Boolean getEmailExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}