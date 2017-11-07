package io.egen.service;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.domain.Alert;
import io.egen.domain.Metric;
import io.egen.domain.Person;
import io.egen.exceptions.AlertException;
import io.egen.exceptions.MetricException;
import io.egen.repositories.AlertDAO;
import io.egen.repositories.MetricDAO;
import io.egen.rule.OverWeightRule;
import io.egen.rule.UnderWeightRule;

@Service
public class MetricServiceImpl implements MetricService {
	

	 public MetricDAO getMetricDao() {
		return metricDao;
	}

	public void setMetricDao(MetricDAO metricDao) {
		this.metricDao = metricDao;
	}



	@Autowired
	private MetricDAO metricDao;
	@Autowired
	private RulesEngine  rulesEngine ;
	@Autowired
	private AlertDAO alertDao;

	public AlertDAO getAlertDAO() {
		return alertDao;
	}

	public void setAlertDAO(AlertDAO alertDAO) {
		this.alertDao = alertDAO;
	}

	public RulesEngine  getRulesEngine() {
		return rulesEngine;
	}

	public void setRulesEngine(RulesEngine  rulesEngine) {
		this.rulesEngine = rulesEngine;
	}

	@Override
	public List<Metric> readByTimeRange(String timeStamp1, String timeStamp2) {
		List<Metric> listOfMetrics=metricDao.readByTimeRange(timeStamp1, timeStamp2);
		
		if(listOfMetrics !=null && listOfMetrics.size() == 0) {
			throw new MetricException("Metrics not found between :" + timeStamp1 + ":" + timeStamp1);
		}
		return listOfMetrics;
	}

	@Override
	public List<Metric> readAllMetrics() {
		List<Metric> listOfMetrics =metricDao.readAllMetrics();
		if(listOfMetrics !=null && listOfMetrics.size() == 0) {
			//throw new MetricException("Metrics not found ");
		}
		return listOfMetrics;
	}

	@Override
	public Key<Metric> create(Metric metric) {
		Alert alert=null;
		Key<Metric> key =  metricDao.save(metric);
		if(key ==null ) {
			throw new MetricException("Metrics not created ");
		}
		
		Person person= new Person(metric.getValue());
		UnderWeightRule underWeightRule = new UnderWeightRule(person);
		OverWeightRule overWtRule= new OverWeightRule(person);
		rulesEngine.registerRule(underWeightRule);
		rulesEngine.registerRule(overWtRule);
		rulesEngine.fireRules();
	
		if(person.isOverWeight()) {
			alert= new Alert();
			alert.setAlert(OVER_WEIGHT_ALERT);
			alert.setAlert_timestamp(String.valueOf(System.currentTimeMillis()));
			
			
		} else if (person.isUnderWeight()){
			
			alert= new Alert();
			alert.setAlert(UNDER_WEIGHT_ALERT);
			alert.setAlert_timestamp(String.valueOf(System.currentTimeMillis()));
			
		}
		else {
			System.out.println("No Alert: Alert not created");
		}
		if(null!= alert) {
			Key<Alert> keyAlert=alertDao.save(alert);
			if(keyAlert ==null ) {
				throw new AlertException("alert not created ");
			}
		}
		
		return key;
	}

	@Override
	public List<Alert> readAllAlerts() {
		List<Alert> listOfAlerts =alertDao.readAllAlerts();
		if(listOfAlerts !=null && listOfAlerts.size() == 0) {
			throw new AlertException("Alerts not found ");
		}
		return listOfAlerts;
	}

	@Override
	public List<Alert> readAlertsByTimeRange(String timeStamp1, String timeStamp2) {
		List<Alert> listOfAlerts=alertDao.readByTimeRange(timeStamp1, timeStamp2);
		
		if(listOfAlerts !=null && listOfAlerts.size() == 0) {
			throw new MetricException("Alerts not found between :" + timeStamp1 + ":" + timeStamp1);
		}
		return listOfAlerts;
	}

}
