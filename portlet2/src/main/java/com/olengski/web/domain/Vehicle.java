package com.olengski.web.domain;

public class Vehicle {
	private String vin;
	private String year;
	private String make;
	private String model;

	public String getVin() {
		return vin;
	}

	public String getYear() {
		return year;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void populateWithSample() {
		int appender = getNextNumber();
		vin = "Sample Value vin " + appender;
		year = "Sample Value year " + appender;
		make = "Sample Value make " + appender;
		model = "Sample Value model " + appender;
	}

	static int sampleCounter = 0;

	private static int getNextNumber() {
		sampleCounter++;
		return sampleCounter;
	}

	public String toString() {
		return "vin = " + vin + ", year = " + year + ", make = " + make + ", model = " + model;
	}
}
