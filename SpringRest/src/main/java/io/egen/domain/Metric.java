package io.egen.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value = "metrics", noClassnameStored = true)
@Indexes(@Index(fields = @Field("timestamp")))
public class Metric {

	@Id
	private ObjectId objectID;

	public ObjectId getObjectID() {
		return objectID;
	}

	public void setObjectID(ObjectId objectID) {
		this.objectID = objectID;
	}

	/*public double getWeight_of_person() {
		return value;
	}

	public void setWeight_of_person(double weight_of_person) {
		this.value = weight_of_person;
	}*/

/*	public String getTimestamp() {
		return timeStamp;
	}

	public void setTimestamp(String timestamp) {
		this.timeStamp = timestamp;
	}*/

	// Weight of person
	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	// Time of Weight
	private String timeStamp;

	public String toString() {

		StringBuffer buffer = new StringBuffer();
		buffer.append("weight of person:" + this.value).append("timeStamp:" + this.timeStamp);
		return buffer.toString();
	}

}
