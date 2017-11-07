package io.egen.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

import io.egen.domain.Alert;


public class AlertDAOImpl extends BasicDAO<Alert, ObjectId> implements AlertDAO  {
	
	public AlertDAOImpl(MongoClient mongo, Morphia morphia, String dbName) {
        super( mongo, morphia, dbName);
    }

	@Override
	public List<Alert> readByTimeRange(String timeStamp1, String timeStamp2) {
		List<Alert> result= createQuery()
			     .filter("alert_timestamp < ", timeStamp1)
			     .filter("alert_timestamp > ", timeStamp2)
			     .asList();
		return result;
	}

	@Override
	public List<Alert> readAllAlerts() {
		List<Alert> result= find().asList();
		return result;
	}

}
