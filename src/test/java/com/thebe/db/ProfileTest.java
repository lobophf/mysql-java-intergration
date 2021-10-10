package com.thebe.db;

import org.junit.Assert;
import org.junit.Test;

public class ProfileTest{
	@Test
	public void loadDoConfig() {
		var props = Profile.getProperties("db");
		Assert.assertNotNull("cannot load db properties", props);

		var dbName = props.getProperty("database");
		
		Assert.assertEquals("people", dbName);
	}
}