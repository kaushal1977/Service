package io.egen.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.mongodb.morphia.Key;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import io.egen.domain.Metric;
import io.egen.repositories.MetricDAO;


public class MetricDAOImplTest {

	
	MetricDAO metricDao = (MetricDAO) new ClassPathXmlApplicationContext("/application-context.xml").getBean("metricDao");
    private final static Logger LOGGER = Logger
			.getLogger(MetricDAOImplTest.class);
    @Test
    public void testPersistAndRestore() throws Exception {
        Metric metric = new Metric();
        metric.setValue(49.5);
        metric.setTimeStamp(String.valueOf((System.currentTimeMillis())));
        Key<Metric> k= metricDao.save(metric);
        
        LOGGER.debug("Metric saved in databse" +k.toString() );
        ObjectId id = metric.getObjectID();
        assertNotNull(id);
        LOGGER.debug("Object saved in databse2" +id );
        Metric remetric = metricDao.get(id);
        LOGGER.debug("Object saved in databse2" +metric.toString() );
        LOGGER.debug("Object saved in databse2" +remetric.toString() );
        assertEquals(metric.getValue(), remetric.getValue(),49.5);
        assertEquals(metric.getTimeStamp(), remetric.getTimeStamp());
    }
	
	
	@Test
	public void test() {
		//alert("Not yet implemented");
	}
	
}
