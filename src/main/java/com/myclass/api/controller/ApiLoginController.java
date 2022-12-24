//package com.myclass.api.controller;
//
//import java.util.Date;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myclass.dto.AuthenticateInfo;
//import com.myclass.dto.LoginInfo;
//import com.myclass.service.ApiLoginService;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@RestController
//@RequestMapping("/api")
//public class ApiLoginController {
//
//	@Autowired
//	private AuthenticationManager authManager;
//
//	@Autowired
//	private ApiLoginService apiLoginService;
//
//	@PostMapping("/login")
//	public ResponseEntity<Object> loginIndex(@Valid @RequestBody LoginInfo loginData, HttpServletResponse response,
//			BindingResult errors) {
//		Authentication authenticate = null;
//		AuthenticateInfo info = null;
//		if (errors.hasErrors()) {
//			return new ResponseEntity<Object>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
//		} else {
//			try {
//				authenticate = authManager.authenticate(
//						new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword()));
//				SecurityContextHolder.getContext().setAuthentication(authenticate);
//				if (authenticate.isAuthenticated()) {
//					info = apiLoginService.createUserInfo(loginData.getEmail());
//				}
//				String token = generateToken(authenticate);
//				info.setToken(token);
//				return new ResponseEntity<Object>(info, HttpStatus.OK);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return new ResponseEntity<Object>(HttpStatus.EXPECTATION_FAILED);
//			}
//		}
//	}
//
//	private String generateToken(Authentication authenticate) {
//		final String JWT_SECRET = "someSecretKeys";
//		final long JWT_EXPIRATION = 864000000L;
//
//		Date now = new Date();
//		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//		UserDetails userDetail = (UserDetails) authenticate.getPrincipal();
//		String token = Jwts.builder().setSubject(userDetail.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
//				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
//		return token;
//
//	}
//
//}
