package io.egen;

import java.net.URI;
import java.util.List;

import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.egen.domain.Alert;
import io.egen.domain.Metric;
import io.egen.service.MetricService;

@SpringBootApplication
@ComponentScan({ "io.egen.controller", "io.egen.repositories","io.egen.domain", "io.egen.util","io.egen.service","io.egen.rule" })
@ImportResource("classpath:application-context.xml")
@RestController
public class MetricsController {
	
	
	 public MetricService getMetricService() {
		return metricService;
	}

	public void setMetricService(MetricService metricService) {
		this.metricService = metricService;
	}



	@Autowired
	private MetricService metricService;
	 
	
	public MetricsController() {
		
		}
	
	public static void main(String[] args) {
		SpringApplication.run(MetricsController.class, args);
	}
	

  
	
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public ResponseEntity<List<Metric>> getAllMetrics() {
		
		List<Metric> listOfMetrics =metricService.readAllMetrics();
		
		return new ResponseEntity<> (listOfMetrics,HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/create", consumes="application/json")
	 public ResponseEntity<?> createMetrics(@RequestBody Metric metric) {
		Key<Metric> key =  metricService.create(metric);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newMetricUri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(key.getId())
				.toUri();
		responseHeaders.setLocation(newMetricUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED); 
		
		
	}
	
	
	@RequestMapping(value="/readByTimeRange",method=RequestMethod.GET)
	public ResponseEntity<List<Metric>> readByTimeRange(@RequestBody String timeStamp1, String timeStamp2 ) {
		List<Metric> listOfMetrics=metricService.readByTimeRange(timeStamp1, timeStamp2);
		return new ResponseEntity<> (listOfMetrics,HttpStatus.OK);
	}
	
	@RequestMapping(value="/alert", method=RequestMethod.GET)
	public ResponseEntity<List<Alert>> getAllAlerts() {
		
		List<Alert> listOfAlerts =metricService.readAllAlerts();
		
		return new ResponseEntity<> (listOfAlerts,HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/readAlertByTimeRange",method=RequestMethod.GET)
	public ResponseEntity<List<Alert>> readAlertByTimeRange(@RequestBody String timeStamp1, String timeStamp2 ) {
		List<Alert> listOfAlerts=metricService.readAlertsByTimeRange(timeStamp1, timeStamp2);
		return new ResponseEntity<> (listOfAlerts,HttpStatus.OK);
	}

}
