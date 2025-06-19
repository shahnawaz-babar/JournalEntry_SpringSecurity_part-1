package net.engineeringdigest.journalApp.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsSercie;

	@Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	 {
		 return http.authorizeHttpRequests(request -> request
				 .requestMatchers("/public/**").permitAll()
				 .requestMatchers("/user/**","/journal/**").authenticated()
				 .requestMatchers("/admin/**").hasRole("ADMIN")
				 .anyRequest().authenticated())
				 .httpBasic(Customizer.withDefaults())
				 .csrf(AbstractHttpConfigurer::disable)
				 .build();
	 }
	 
	 @Autowired
	 public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	 {
		 auth.userDetailsService(userDetailsSercie).passwordEncoder(passwordEncoder());
	 }
	
	 @Bean
	 public PasswordEncoder passwordEncoder()
	 {
		 return new BCryptPasswordEncoder();
	 }
	 
}
