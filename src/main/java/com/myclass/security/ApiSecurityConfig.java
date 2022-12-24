//package com.myclass.security;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.myclass.filter.JWTAuthorizationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	private UserDetailsService service;
//
//	@Bean
//	public PasswordEncoder passEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//	@Bean
//	@Override
//	protected AuthenticationManager authenticationManager() throws Exception {
//		// TODO Auto-generated method stub
//		return super.authenticationManager();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(service).passwordEncoder(passEncoder());
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.cors();
//
//		http.csrf().disable().antMatcher("/api/*").authorizeRequests().antMatchers("/api/login").permitAll()
//				.antMatchers("/api/category/index", "/api/category/getIcon").permitAll()
//				.antMatchers("/api/category/add", "/api/category", "/api/category/update", "/api/category/uploadIcon")
//				.hasAnyRole("ADMIN", "MANAGER", "TEACHER").antMatchers("/api/category/delete").hasAnyRole("ADMIN")
//				.antMatchers("/api/role/index").hasAnyRole("ADMIN", "MANAGER")
//				.antMatchers("/api/role/add", "/api/role", "/api/role/update", "/api/role/delete").hasAnyRole("ADMIN")
//				.antMatchers("/api/user/index", "/api/user/add").hasAnyRole("ADMIN", "MANAGER")
//				.antMatchers("/api/user", "/api/user/update", "/api/user/uploadAvatar")
//				.hasAnyRole("ADMIN", "MANAGER", "TEACHER", "USER").antMatchers("/api/user/delete").hasAnyRole("ADMIN")
//				.antMatchers("/api/user/getAvatar").permitAll().antMatchers("/api/course/index", "/api/course/getImage")
//				.permitAll().antMatchers("/api/course/add").hasAnyRole("TEACHER")
//				.antMatchers("/api/course", "/api/course/update", "/api/course/uploadImage")
//				.hasAnyRole("ADMIN", "MANAGER", "TEACHER").antMatchers("/api/course/delete")
//				.hasAnyRole("ADMIN", "MANAGER").antMatchers("/api/video/upload").permitAll()
//				.antMatchers("/api/video/watch").permitAll().anyRequest().authenticated();
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), service));
//	}
//}