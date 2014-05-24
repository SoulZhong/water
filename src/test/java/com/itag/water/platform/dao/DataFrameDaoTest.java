package com.itag.water.platform.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itag.water.platform.domain.DataFrame;

public class DataFrameDaoTest {

	DataFrameDao dao;
	SessionFactory factory;

	@Before
	public void setUp() throws Exception {

		Configuration config = new Configuration();

		Properties jdbcConfig = new Properties();
		jdbcConfig.load(new FileReader(new File("/udpserver/jdbc-test.properties")));
		config.addProperties(jdbcConfig);

		factory = config.configure().buildSessionFactory();
		dao = new DataFrameDao(factory);
	}

	@After
	public void tearDown() throws Exception {

		factory.close();
	}

	@Test
	public void testSave() {

		DataFrame dataFrame = new DataFrame();
		dao.save(dataFrame);

		dao.delete(dataFrame);
	}

}
