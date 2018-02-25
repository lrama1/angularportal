package com.olengski.web.domain;

public class Person {
	private String personId;
	private String firstName;
	private String lastName;

	public String getPersonId() {
		return personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void populateWithSample() {
		int appender = getNextNumber();
		personId = "Sample Value personId " + appender;
		firstName = "Sample Value firstName " + appender;
		lastName = "Sample Value lastName " + appender;
	}

	static int sampleCounter = 0;

	private static int getNextNumber() {
		sampleCounter++;
		return sampleCounter;
	}

	public String toString() {
		return "personId = " + personId + ", firstName = " + firstName + ", lastName = " + lastName;
	}
}
