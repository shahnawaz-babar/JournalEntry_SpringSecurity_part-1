package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

@Component
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public void saveEntry(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("USER"));
		userRepository.save(user);
	}

	public void saveNewUser(User user) {

		userRepository.save(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> findById(ObjectId myId) {
		return userRepository.findById(myId);
	}

	public void deleteById(ObjectId myId) {
		userRepository.deleteById(myId);
	}

	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);

	}
}
