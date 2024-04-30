package com.ecw.backendapi.service;

import org.springframework.stereotype.Service;

import com.ecw.backendapi.entity.User;
import com.ecw.backendapi.request.LoginRequest;
@Service
public interface UserService {
	
	public boolean registerUser(User user);
	public boolean loginUser(LoginRequest loginRequest);

}
