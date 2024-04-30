package com.ecw.backendapi.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecw.backendapi.entity.Role;
import com.ecw.backendapi.entity.User;
import com.ecw.backendapi.enums.ERole;
import com.ecw.backendapi.exception.MapValidationErrorService;
import com.ecw.backendapi.repository.RoleRepository;
import com.ecw.backendapi.repository.UserRepository;
import com.ecw.backendapi.request.LoginRequest;
import com.ecw.backendapi.request.SignupRequest;
import com.ecw.backendapi.security.JwtUtils;
import com.ecw.backendapi.service.UserDetailsImpl;
import com.ecw.backendapi.service.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	@Autowired
	private JwtUtils jwatUtils;

	@PostMapping("/authuser")
	public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequest loginRequest) {
		Map<String, Object> body = new HashMap<>();
		Optional<User> findByEmail = this.userRepository.findByEmail(loginRequest.getUsername());
		if (findByEmail != null) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal(); // all user details
			String jwtToken = jwatUtils.generateJwtToken(authentication);
			body.put("statusCode", "200");
			body.put("token", jwtToken);
			return new ResponseEntity<>(body, HttpStatus.OK);
		}
		return null;
	}

	@PostMapping("signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, BindingResult result) {
		Map<String, String> errors = new HashMap<>();
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		} else {
			Map<String, Object> body = new HashMap<>();
			if (userRepository.existsByEmail(signUpRequest.getEmail())) {

				body.put("message", "Your Account is Allready Exists - " + signUpRequest.getEmail());
				body.put("status", "409");
				return new ResponseEntity<>(body, HttpStatus.CONFLICT);
			} else {
				User userEntity = this.mapper.map(signUpRequest, User.class);

				/* == encoded password== */
				String encode = encoder.encode(userEntity.getPassword());
				userEntity.setPassword(encode);
				signUpRequest.setRole(signUpRequest.getRole());
				System.out.println("set Role is :: " + signUpRequest.getRole());
				Set<String> strRoles = signUpRequest.getRole();
				System.out.println(" get Role is :: " + strRoles);
				Set<Role> roles = new HashSet<>();
				System.out.println(" Add Role is :: " + roles);
				if (strRoles == null) {
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
					System.out.println(" Add Role is :: " + roles);
				} else {
					strRoles.forEach(role -> {
						switch (role) {
						case "admin":
							Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(adminRole);
							System.out.println(" Add Role is :: " + roles);

							break;
						case "mod":
							Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(modRole);
							System.out.println(" Add Role is :: " + roles);

							break;
						default:
							Role userRole = roleRepository.findByName(ERole.ROLE_USER)
									.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
							roles.add(userRole);
							System.out.println(" Add Role is :: " + roles);
						}
					});
				}
				userEntity.setRoles(roles);
				userEntity.setFirstname(signUpRequest.getFirstname());
				userEntity.setRegisteron(new Date());
				// userEntity.setUsername(RandomStringValue.getAlphaNumericString());
				User save = userRepository.save(userEntity);
//		if (save instanceof User) {
//			this.emailDetails.setRecipient(save.getEmail());
//			this.emailDetails.setMsgBody("Your Account Is Created");
//			this.emailDetails.setSubject("EPDS ACCOUNT");
//			String sendSimpleMail = this.emailService.sendSimpleMail(emailDetails);
//			body.put("message", sendSimpleMail);
//			body.put("status", "200");
//			return new ResponseEntity<>(body, HttpStatus.OK);
//		}
				// ModelAndView modelAndView = new ModelAndView("api/test/Sign-Up");
				body.put("message", "Your Account Is Created !!");
				body.put("status", "200");
				return new ResponseEntity<>(body, HttpStatus.OK);
			}
		}
	}

}
