package io.egen.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.Key;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.egen.domain.Alert;
import io.egen.repositories.AlertDAO;

public class AlertDaoImplTest {

	AlertDAO alertDao = new ClassPathXmlApplicationContext("/application-context.xml").getBean(AlertDAO.class);
	private final static Logger LOGGER = Logger.getLogger(AlertDaoImplTest.class);

	@Test
	public void testPersistAndRestore() throws Exception {
		Alert alert = new Alert();
		alert.setAlert("person is underweight");
		alert.setAlert_timestamp(String.valueOf((System.currentTimeMillis())));
		Key<Alert> k = alertDao.save(alert);

		LOGGER.debug("Object saved in databse" + k.toString());
		ObjectId id = alert.getObjectID();
		assertNotNull(id);
		LOGGER.debug("Object saved in databse2" + id);
		Alert reAlert = alertDao.get(id);
		LOGGER.debug("Object saved in databse2" + reAlert.toString());
		LOGGER.debug("Object saved in databse2" + reAlert.toString());
		assertEquals(alert.getAlert(), reAlert.getAlert());
		assertEquals(alert.getAlert_timestamp(), reAlert.getAlert_timestamp());
	}

	@Test
	public void testRead() {
		// alert("Not yet implemented");
		List<Alert> result = alertDao.readAllAlerts();
		LOGGER.debug("result" + result.toString());

	}

	@Test
	public void testReadByTimeStamp() {

		String timeStamp1 = "1462715996240";
		String timeStamp2 = "1462715982750";

		List<Alert> result = alertDao.readByTimeRange(timeStamp1, timeStamp2);

		assertNotNull(result.get(0));
		LOGGER.debug("result" + result.get(0));

	}

}
