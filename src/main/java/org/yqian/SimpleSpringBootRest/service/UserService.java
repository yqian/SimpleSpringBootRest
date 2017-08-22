package org.yqian.SimpleSpringBootRest.service;
import java.util.List;
import org.yqian.SimpleSpringBootRest.model.User;
public interface UserService {
 List<User> listUser();
 User getUser(int id);
 void updateUser(User u);
 void deleteUser(int id);
 void deleteAllUser();}
