package com.mredenius003.realestatewebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mredenius003.realestatewebapp.model.User;

/**
 * API to abstract the retrieval of a {@link User} from a database.
 * 
 * @author mredenius003
 *
 */
@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
