package net.engineeringdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;


@Component
public class UserDetailsServiceImpl  implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUserName(username);
		if(user!=null)
		{
			UserDetails userDetails=org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
			.password(user.getPassword())			
			.roles(user.getRoles().toArray(new String[0]))
			.build();
			return userDetails;
			
		}
		
		throw new UsernameNotFoundException("User not found Exception " +username);
	}

}
