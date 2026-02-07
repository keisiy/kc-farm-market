package com.kc.farm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.farm.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/* JPAに対する注文書　「Emailで、検索し、存在するかどうかを調べて」 */
	boolean existsByEmail(String email);
	
}
