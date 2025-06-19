package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;

 @RestController
 @RequestMapping("/public")

public class PublicController {
	 
	 @Autowired
	 private UserService userService;

	 @PostMapping 
		public void createUser(@RequestBody User user)
		{
			userService.saveEntry(user);
		}
		
	 
}
