package io.egen.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value = "alerts", noClassnameStored = true)
@Indexes(@Index(fields = @Field("alert_timestamp")))
public class Alert {

	@Id
	private ObjectId objectID;

	public ObjectId getObjectID() {
		return objectID;
	}

	public void setObjectID(ObjectId objectID) {
		this.objectID = objectID;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getAlert_timestamp() {
		return alert_timestamp;
	}

	public void setAlert_timestamp(String alert_timestamp) {
		this.alert_timestamp = alert_timestamp;
	}

	private String alert;
	private String alert_timestamp;
	
	public String toString(){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("alert:" + this.alert)
			  .append("timestampt" + this.alert_timestamp);
		return buffer.toString();
	}

}
