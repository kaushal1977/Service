package io.egen.rule;

import org.easyrules.core.BasicRule;

import io.egen.domain.Person;


public class UnderWeightRule extends BasicRule {

	private static final double ADULT_WEIGHT =150;

	private Person person;
	
	UnderWeightRule(){
		
	}

	public UnderWeightRule(Person person) {
		super("Under Weight Rule","under weight",2);
		this.person = person;

	}

	@Override
	public boolean evaluate() {
		return person.getWeight() < ADULT_WEIGHT- ADULT_WEIGHT/10;
	}

	@Override
	public void execute() {
		person.setUnderWeight(true);
		System.out.println("person is underweight");
	}

}
