package io.egen.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import io.egen.domain.Alert;

public interface AlertDAO  extends DAO<Alert, ObjectId> {
	

	List<Alert> readByTimeRange(String timeStamp1, String timeStamp2);
	
	List<Alert> readAllAlerts() ;
	
	
}
