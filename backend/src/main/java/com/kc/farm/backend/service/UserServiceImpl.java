package com.kc.farm.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kc.farm.backend.dto.UserCreateRequest;
import com.kc.farm.backend.dto.UserResponse;
import com.kc.farm.backend.entity.User;
import com.kc.farm.backend.exception.DuplicateEmailException;
import com.kc.farm.backend.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	/* DBアクセス */
	private UserRepository userRepository;
	/* パスワードエンコード */
	private PasswordEncoder passwordEncoder;
	
	/* コンストラクタインジェクション */
	public UserServiceImpl(UserRepository userRepository,
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	/* User登録 */
	@Override
	public UserResponse create(UserCreateRequest request) {

		/* メール重複チェック */
		boolean isExistsByEmail = userRepository.existsByEmail(request.getEmail());
		if (isExistsByEmail) {
			/* すでに存在する場合 */
			throw new DuplicateEmailException(request.getEmail());
		}
		
		/* パスワード暗号化 */
		String encryptedPass = passwordEncoder.encode(request.getPassword());
		
		/* Entity作成 */
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encryptedPass);
		user.onCreated();
		
		/* DB保存 */
		User saved = userRepository.save(user);
		
		/* Response作成 */
		return new UserResponse(
				saved.getId(),
				saved.getEmail(),
				saved.getCreatedAt()
			);
	}

}
