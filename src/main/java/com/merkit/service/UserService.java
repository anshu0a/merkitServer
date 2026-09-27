package com.merkit.service;

import java.util.List;

import com.merkit.dto.req.SocalLinkRequest;
import com.merkit.dto.res.ApiResponse;
import com.merkit.dto.res.ProfileResponse;
import com.merkit.dto.res.UserResponse;

public interface UserService {

    UserResponse getUserById(Long id);

    UserResponse getUserByUsername(String username);
    ProfileResponse getUserByUsernameForProfile(String username);
    
    Boolean getUserNameExist(String username);
    Boolean getEmailExist(String email);

    List<UserResponse> getAllUsers();

    ApiResponse enableUser(Long id);
    ApiResponse disableUser(Long id);
    ApiResponse deleteUser(Long id);
    
    List<String[]> searchUser(String regx);
    ApiResponse addSocalLink(SocalLinkRequest socalLink, String username);
    ApiResponse deleteSocalLink(String key, String username);

}