package com.kc.farm.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kc.farm.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	/* JPAに対する注文書　「Emailで、検索し、存在するかどうかを調べて」 */
	boolean existsByEmail(String email);
	
	/* JPAに対する注文書　「Emailで検索し、Optional<User>を返却して」 */
	Optional<User> findByEmail(String email);
	
}
