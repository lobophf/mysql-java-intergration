package com.thebe.db;

import org.junit.Assert;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDAOImplTest {
	
	private Connection conn;
	
	@Before
	public void setUp() throws SQLException {
		var props = Profile.getProperties("db");
		var db = Database.instance();  
		db.connect(props);
		conn = db.getConnection();
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		Database.instance().close();
	}
	
	@Test
	public void testSave() throws SQLException {
		User user = new User("Earth");
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		var stmt = conn.createStatement();
		var rs = stmt.executeQuery("select id, name from user order by id desc");	
		rs.next();
		
		Assert.assertTrue("cannot assert true", rs.next());
		
		var name = rs.getString("name");
		Assert.assertEquals("user name does't match retrieved", user.getName(), name);
		stmt.close();
		
	}
}
