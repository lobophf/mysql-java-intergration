package com.thebe.db;
import java.sql.SQLException;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
		
		Properties props = Profile.getProperties("db");
		var db = Database.instance();
		
		try {
			db.connect(props);
		} catch (SQLException e) {
			System.out.println("Cannot connect to database.");
		}
		
		System.out.println("Connected");
		UserDao userDao = new UserDaoImpl();
		userDao.save(new User("Mars"));
		userDao.save(new User("Jupter"));
		userDao.save(new User("Earth"));

		var users = userDao.getAll();
		users.forEach(System.out::println);
		
		var user = userDao.findById(4);
		System.out.println(user);
		
		userDao.delete(new User(1, null));
		users.forEach(System.out::println);
		var userOpt = userDao.findById(2);
		
		if(userOpt.isPresent()) {
			User u = userOpt.get();
			u.setName("Neptune");
			userDao.update(u);
		}
			
		try {
			db.close();
		} catch (SQLException e) {
			System.out.println("Cannot close database connection.");
		}
		System.out.println("Closed");
	}
}
