package io.egen.repositories;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;

import io.egen.domain.Metric;


public class MetricDAOImpl extends BasicDAO<Metric, ObjectId> implements MetricDAO{

	
	MetricDAOImpl(MongoClient mongo, Morphia morphia, String dbName){
		super(mongo, morphia, dbName);
	}

	@Override
	public List<Metric> readByTimeRange(String timeStamp1, String timeStamp2) {
		List<Metric> result= createQuery()
			     .filter("alert_timestamp < ", timeStamp1)
			     .filter("alert_timestamp > ", timeStamp2)
			     .asList();
		return result;
		
	}

	@Override
	public List<Metric> readAllMetrics() {
		List<Metric> result= find().asList();
		return result;
	}
}
