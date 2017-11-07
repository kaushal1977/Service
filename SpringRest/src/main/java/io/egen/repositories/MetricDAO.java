package io.egen.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import io.egen.domain.Metric;

public interface MetricDAO extends DAO<Metric, ObjectId> {

	List<Metric> readByTimeRange(String timeStamp1, String timeStamp2);

	List<Metric> readAllMetrics();
	
}
