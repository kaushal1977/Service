package io.egen.domain;


/**
 * @author Kaushal.Kishore
 *
 */
public class Person {

    public Person () {
    	
    }

  
    public Person (double weight) {
    	this.weight=weight;
    }
    
	public double getWeight() {
		return weight;
	}

	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isUnderWeight() {
		return underWeight;
	}

	public void setUnderWeight(boolean underWeight) {
		this.underWeight = underWeight;
	}

	public boolean isOverWeight() {
		return overWeight;
	}

	public void setOverWeight(boolean overWeight) {
		this.overWeight = overWeight;
	}

	private double weight;
	private boolean underWeight = false;
	private boolean overWeight = false;

}
