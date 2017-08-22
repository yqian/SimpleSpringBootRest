package org.yqian.SimpleSpringBootRest.service;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;import org.springframework.test.annotation.Rollback;import org.springframework.test.context.junit4.SpringRunner;import org.yqian.SimpleSpringBootRest.model.User;import org.yqian.SimpleSpringBootRest.repository.UserRepository;
@RunWith(SpringRunner.class)@DataJpaTestpublic class UserServiceImplTest {
 @Autowired private UserRepository userRepository;
 @Test public void testListUser() {  List<User> uList = userRepository.findAll();  int expected = 3;  assertThat(uList.size()).isEqualTo(expected); }
 @Test public void testGetUser() {  User u = userRepository.getOne(1);  String expected = "Big Boss";  assertThat(u.getRole()).isEqualTo(expected); }
 @Test public void testUpdateUser() {  User u = new User();  u.setId(1);  u.setName("Joe Schmo");  u.setRole("U R Fired");  userRepository.save(u);  User user = userRepository.getOne(1);  assertThat(user.getRole()).isEqualTo("U R Fired"); }
 @Test @Rollback public void testDeleteUser() {  userRepository.delete(10);  List<User> uList = userRepository.findAll();  int expected = 2;  assertThat(uList.size()).isEqualTo(expected); }
 @Test @Rollback public void testDeleteAllUser() {  userRepository.deleteAll();  List<User> uList = userRepository.findAll();  assertThat(uList).isEmpty(); }}
