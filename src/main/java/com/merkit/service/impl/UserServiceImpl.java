package com.merkit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.merkit.dto.req.SocalLinkRequest;
import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.ProfileResponse;
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

	@Override
	public UserResponse getUserById(Long id) {
		User user = getUser(id);
		return userMapper.toResponse(user);
	}

	@Override
	public UserResponse getUserByUsername(String username) {
		User user = getUser(username);
		return userMapper.toResponse(user);
	}

	@Override
	public List<UserResponse> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(userMapper::toResponse).toList();
	}

	@Override
	public Boolean getUserNameExist(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	@Override
	public Boolean getEmailExist(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

	@Override
	public List<String[]> searchUser(String regx) {
		List<User> allUsers = userRepository.findTop7ByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(regx,
				regx);

		return allUsers.stream().map(one -> {
			return new String[] { one.getUsername(), one.getName(), one.getProfilepic() };
		}).toList();
	}

	@Override
	public ProfileResponse getUserByUsernameForProfile(String username) {
		
		User u = getUser(username);
		
		List<String[]> scl = u.getSocalLinks() == null
		        ? new ArrayList<>()
		        : u.getSocalLinks().entrySet().stream()
		            .map(one -> new String[]{one.getKey(), one.getValue()})
		            .toList();
		
		return ProfileResponse.builder()
		.id(u.getId())
		.username(u.getUsername())
		.name(u.getName())
		.bio(u.getBio())
		.profilepic(u.getProfilepic())
		.email(u.getEmail())
		.SocalLinks(scl)
		.createdAt(u.getCreatedAt())
		.updatedAt(u.getUpdatedAt())
		.lastLoginAt(u.getLastLoginAt())
		.loginHistory(u.getLoginHistory())
		.build();
	}
	
	@Override
	public ApiResponse addSocalLink(SocalLinkRequest socalLink, String username) {
		
		User u = getUser(username);
		
		if(u.getSocalLinks() == null ) u.setSocalLinks(new HashMap<>());
		u.getSocalLinks().put(socalLink.getName(), socalLink.getLink());
		
		userRepository.save(u);
		
		return ApiResponse.builder()
				.success(true)
				.message("Socal Link Added Successfully")
				.statusCode(200)
				.build();
	}
	
	@Override
	public ApiResponse deleteSocalLink(String key, String username) {
		User u = getUser(username);
		
		if(u.getSocalLinks() == null ) u.setSocalLinks(new HashMap<>());
		else u.getSocalLinks().remove(key);
		
		userRepository.save(u);
		
		return ApiResponse.builder()
				.success(true)
				.message("Socal Link Removed Successfully")
				.statusCode(200)
				.build();
	}

	// ---------------------------- unused ----------------------------------
	@Override
	public ApiResponse enableUser(Long id) {

		User user = getUser(id);
		user.setEnabled(true);
		userRepository.save(user);

		return ApiResponse.builder().success(true).message("User enabled successfully.").build();
	}

	@Override
	public ApiResponse disableUser(Long id) {
		User user = getUser(id);
		user.setEnabled(false);
		userRepository.save(user);

		return ApiResponse.builder().success(true).message("User disabled successfully.").build();
	}

	@Override
	public ApiResponse deleteUser(Long id) {

		User user = getUser(id);
		userRepository.delete(user);

		return ApiResponse.builder().success(true).message("User deleted successfully.").build();
	}
	


	
	
//	----------------------------- healper ---------------------------------- 

	private User getUser(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

	private User getUser(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
	}


}