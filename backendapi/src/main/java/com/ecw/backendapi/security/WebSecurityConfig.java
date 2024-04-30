package com.ecw.backendapi.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import com.ecw.backendapi.service.UserDetailsServiceImpl;
@Configuration
@EnableGlobalMethodSecurity(
		// securedEnabled = true,
		// jsr250Enabled = true,
		prePostEnabled = true)
@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//@Slf4j
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;

//	@Autowired
//	CacheControlFilter cachecontrolfilter;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

//	@Bean
//	public AuthTokenFilter authenticationJwtTokenFilter() {
//		return new AuthTokenFilter();
//	}
//  @Override
//  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//  }
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

//  @Bean
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.cors().and().csrf().disable()
//      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//      .antMatchers("/api/test/**").permitAll()
//      .anyRequest().authenticated();
//
//    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//  }
	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception {
	 * 
	 * http.cors().and().csrf().disable().exceptionHandling().
	 * authenticationEntryPoint(unauthorizedHandler).and()
	 * .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
	 * and().authorizeRequests()
	 * .antMatchers("/SignUp","/login","/api/auth/authuser","/api/auth/signin",
	 * "/api/auth/signup","/resources/js/**","/resources/css/**",
	 * "/resources/images/**","resources/**").permitAll()
	 * .antMatchers("/dashboard","/save","/savemenu","/api/banner/addbanner").
	 * permitAll() .anyRequest().authenticated();
	 * http.authenticationProvider(authenticationProvider());
	 * 
	 * http.httpBasic(); return http.build(); }
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf(csrf -> csrf.disable()).exceptionHandling(handling -> handling.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)).authorizeRequests(requests -> requests
                .requestMatchers("/api/auth/**","/api/auth/signup","/api/category/**")
                .permitAll().anyRequest().authenticated());
		http.authenticationProvider(authenticationProvider());
		// http.addFilterAfter(authenticationJwtTokenFilter(),
		// UsernamePasswordAuthenticationFilter.class);
		//http.addFilterBefore(authenticationJwtTokenFilter(),
		// UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

// ============ FOR BASIC AUTHENTICATION===========

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
	 * expressionInterceptUrlRegistry.antMatchers("/api/auth/authuser").permitAll()
	 * .anyRequest().authenticated()) .httpBasic(httpSecurityHttpBasicConfigurer ->
	 * httpSecurityHttpBasicConfigurer.authenticationEntryPoint(unauthorizedHandler)
	 * ); http.addFilterAfter(new AuthTokenFilter(),
	 * BasicAuthenticationFilter.class); return http.build(); }
	 */

	/* ignore the static contentg */

	// filter Cros Bean

//    public FilterRegistrationBean corsFilter() {
//    	FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
//    	filterRegistrationBean.setEnabled(true);
//    	
//    	return filterRegistrationBean;
//    	
//    }

	@Bean
	public FilterRegistrationBean corsFilterRegistrationBean() {
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedOrigin("http://localhost:4200"); // Allow all origins
		config.addAllowedHeader("http://localhost:4200"); // Allow all headers
		config.addAllowedMethod("http://localhost:4200"); // Allow all HTTP methods
		config.setAllowCredentials(true); // Allow credentials such as cookies
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(0); // Set the CORS filter to have the highest precedence
		return bean;
	}

//	@Bean
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/UploadFiles/**");
//	}

}
