package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

//	@Query(value = "select * from user where email=:email or mobile=:mobile" , nativeQuery = true)
//	User findByEmailOrMobile(@Param("email") String email, @Param("mobile") String mobile);
	
	@Query(value = "select u from User u where u.email=:email or u.mobile=:mobile")
	User findByEmailOrMobile(@Param("email") String email, @Param("mobile") String mobile);
	
}
