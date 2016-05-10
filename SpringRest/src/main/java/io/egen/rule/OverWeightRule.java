package io.egen.rule;

import org.easyrules.core.BasicRule;

import io.egen.domain.Person;


public class OverWeightRule extends BasicRule {

	private static final double ADULT_WEIGHT = 150;

	private Person person;
	
	public OverWeightRule() {
		
	}

	public OverWeightRule(Person person) {
		super("Over Weight Rule","person weight is overage",1);
		this.person = person;

	}

	@Override
	public boolean evaluate() {
		return person.getWeight() > (ADULT_WEIGHT- ADULT_WEIGHT/10);
	}

	@Override
	public void execute() {
		person.setOverWeight(true);
		System.out.println("person is overrweight");
	}

}
