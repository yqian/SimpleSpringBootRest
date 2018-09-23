package org.yqian.SimpleSpringBootRest.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yqian.SimpleSpringBootRest.model.User;
import org.yqian.SimpleSpringBootRest.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public void updateUser(User u) {
		userRepository.save(u);
	}

	@Override
	public void deleteUser(User u) {
		userRepository.delete(u);
	}

	@Override
	public void deleteAllUser() {
		userRepository.deleteAll();
	}
}
