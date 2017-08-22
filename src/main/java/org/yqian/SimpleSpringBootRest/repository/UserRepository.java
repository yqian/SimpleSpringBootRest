package org.yqian.SimpleSpringBootRest.repository;
import org.springframework.data.jpa.repository.JpaRepository;import org.yqian.SimpleSpringBootRest.model.User;
public interface UserRepository extends JpaRepository<User, Integer> {
}
