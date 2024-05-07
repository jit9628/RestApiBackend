package com.ecw.backendapi.security;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ecw.backendapi.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  private UserDetailsServiceImpl userDetailsService;
//  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = parseJwt(request);
     
     // log.info("parsing jwt  :: "+jwt);

      /// check jwt tokens is not noll or valid

      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
       // log.error("UserName after validating : {}", username);

        // loadUserByUserName insiede
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      //  log.error("User Details : {}", userDetails);
        UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      
     // log.info("Filter Authentication is :"+authentication);
//      if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
//    	  response.sendRedirect("/api/page/LogIn");
//      }else {
//    	  response.sendRedirect("/");
//      }
//      
    } catch (Exception e) {
    //  log.error("Cannot set user authentication: {}", e.getMessage());
    }
    filterChain.doFilter(request, response); // FILTERING ALL REQUESTS
  }
//  parsing jwt Tokens
  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }
    return null;
  }
}
