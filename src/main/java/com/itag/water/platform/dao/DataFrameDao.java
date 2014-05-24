/**
 * 
 */
package com.itag.water.platform.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itag.water.platform.domain.DataFrame;

/**
 * @author Soul
 * @date 2014年5月24日
 */
public class DataFrameDao {

	private SessionFactory sessionFactory;

	public DataFrameDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void save(DataFrame dataFrame) {

		Session session = sessionFactory.openSession();

		session.beginTransaction();
		try {
			Serializable d = session.save(dataFrame);

			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

	public void delete(DataFrame dataFrame) {
		Session session = sessionFactory.openSession();

		session.beginTransaction();
		try {
			session.delete(dataFrame);

			session.getTransaction().commit();

		} finally {
			session.close();
		}
	}

}
