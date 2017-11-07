package io.egen.service;

import java.util.List;

import org.mongodb.morphia.Key;

import io.egen.domain.Alert;
import io.egen.domain.Metric;

public interface MetricService {
	
	final String UNDER_WEIGHT_ALERT="person is under weight";
	final String OVER_WEIGHT_ALERT="person is over weight";

	List<Metric> readByTimeRange(String timeStamp1, String timeStamp2);
	public List<Metric> readAllMetrics();
	
	Key<Metric> create(Metric metric);
	List<Alert> readAllAlerts();
	List<Alert> readAlertsByTimeRange(String timeStamp1, String timeStamp2);
	
}
