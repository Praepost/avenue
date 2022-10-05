package com.avenues.avenues.work.user.repository;

import com.avenues.avenues.work.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
//    User findById(Integer id);
}
