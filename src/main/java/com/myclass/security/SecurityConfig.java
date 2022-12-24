package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.myclass.service.impl.SecurityhandlerService;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SecurityhandlerService securityhanlerService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// KHAI BAO SERVICE DUNG DE CHECK EMAIL LAY THONG TIN NGUOI DUNG
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	// CAU HINH PHAN QUYEN
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()	.disable()
		.authorizeRequests()
		.antMatchers("/admin/login","/course","/course/search","/home","/","/user/profile/**")
		.permitAll()
		.antMatchers("/registration**","/forgot-password**","/reset-password**")
		.permitAll()
		.antMatchers("/admin/**")
		.hasAnyRole("ADMIN")
		.antMatchers("/lecturer/**")
		.hasAnyRole("ADMIN","TEACHER")
		.antMatchers("/course/**")
		.hasAnyRole("ADMIN","MEMBER")
		.antMatchers("/user/**")
		.hasAnyRole("ADMIN","MEMBER")
		.anyRequest()
		.authenticated();

		http.exceptionHandling().accessDeniedPage("/error/403");

		// CAU HINH DANG XUAT

		http.logout().logoutUrl("/logout").logoutSuccessUrl("/course").deleteCookies("JSESSIONID");

		// CAU HINH DANG NHAP
		
		http.formLogin().loginPage("/admin/login") 
				.loginProcessingUrl("/admin/login") 
				.usernameParameter("email")
				.passwordParameter("password")
				.successHandler(securityhanlerService)
				.failureUrl("/admin/login?error=1");

	}

	// KHAI BAO DOI TUONG GIAI MA PASSWORD VA CHECKPASS

	// PHAN QUYEN NGUOI DUNG
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**", "/login/**", "/userassets/**","/registration/**","registration/**","/js/**","/css/**","/img/**","/webjars/**");
	}
}
