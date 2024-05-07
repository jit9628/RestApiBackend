package com.ecw.backendapi.request;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Column;



public class SignupRequest {
	private Long id;
	 @NotNull(message = "Please enter firstname")
	 @NotBlank
	 @Column(nullable = false)
	private String firstname;
	 @NotNull(message = "Please enter lastname")
	 @NotBlank
	private String lastname;
	private String username;
	 @NotNull(message = "Please enter email")
	 @NotBlank
	 @Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",message = "Please Valid Email Id")
	private String email;
	 @NotNull(message = "Please enter password")
	 @NotBlank
	private String password;
	 @NotNull(message = "Please enter mobile number")
	 @NotBlank
	 @Pattern(regexp="(^$|[0-9]{10})",message = "Your Phone Number Must Be 10 Digits ")
	private String mobile;
	private Set<String> role;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getRole() {
		return role;
	}
	public void setRole(Set<String> role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
