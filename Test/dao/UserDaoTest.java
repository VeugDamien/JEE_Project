package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	
	private UserDao userdao;

	public UserDaoTest() {
		// TODO Auto-generated constructor stub
		userdao = UserDao.getInstance();
	}
	
	@Test
	public void testGetUser() {
		assertEquals("Un utilisateur doit �tre retourn�", "2/Admin/admin", userdao.getUserByLogin("Admin").toString());
	}

}
