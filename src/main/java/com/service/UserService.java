package com.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.Role;
import com.entity.User;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return user;
	}

	public User findUserById(Long userId) {
		Optional<User> userFromDb = userRepository.findById(userId);
		return userFromDb.orElse(new User());
	}

	public List<User> allUsers() {
		return userRepository.findAll();
	}

	public boolean saveUser(User user) {
		User userFromDB = userRepository.findUserByUsername(user.getUsername());
		if (userFromDB != null) {
			return false;
		}
		user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
		user.setPassword(user.getPassword());
		userRepository.save(user);
		return true;
	}

	public boolean deleteUser(Long userId) {
		if (userRepository.findById(userId).isPresent()) {
			userRepository.deleteById(userId);
			return true;
		}
		return false;
	}

	public List<User> userList() {
		return userRepository.findAll();
	}

}
