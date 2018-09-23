package org.yqian.SimpleSpringBootRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yqian.SimpleSpringBootRest.model.User;
import org.yqian.SimpleSpringBootRest.service.UserService;


@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserService service;

	@GetMapping(value = "/list")
	public ResponseEntity<List<User>> listUsers() {
		List<User> list = service.listUser();
		if (list.isEmpty())
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User u = service.getUser(id);
		if (u == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		System.out.println(u.toString());
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User u) {
		User user = service.getUser(u.getId());
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		service.updateUser(u);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		User user = service.getUser(id);
		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		service.deleteUser(user);
		return new ResponseEntity<User>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/clear")
	public ResponseEntity<List<User>> deleteAllUser() {
		service.deleteAllUser();
		return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
	}
}
