package com.desmond;

import com.desmond.domain.User;
import com.desmond.dao.PermissionDao;
import com.desmond.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void TestBcrypt(){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encode = passwordEncoder.encode("123");
		System.out.println(encode);
	}

	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void testSelectPermsByUserId(){
		List<String> list = permissionDao.getPermissionByUserId("1");
		System.out.println(list);
	}
}
