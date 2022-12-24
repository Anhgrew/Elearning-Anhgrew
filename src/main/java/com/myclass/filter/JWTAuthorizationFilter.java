//package com.myclass.filter;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Jwts;
//
//@Component
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//	@Autowired
//	private UserDetailsService service;
//
//	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
//		super(authenticationManager);
//		service = userDetailsService;
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		final String JWT_SECRET = "someSecretKeys";
//		String token = request.getHeader("Authorization");
//		if (token != null) {
//			String email = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();
//			UserDetails userDetails = service.loadUserByUsername(email);
//			UsernamePasswordAuthenticationToken authenricationToken = new UsernamePasswordAuthenticationToken(
//					userDetails, null, userDetails.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(authenricationToken);
//		}
//		chain.doFilter(request, response);
//	}
//}