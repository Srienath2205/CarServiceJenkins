package com.srienath.restapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleID;

    private String make;
    private String model;
    private int year;
    private String vin;

    @Lob
    private byte[] vehicleImage;

    @Lob
    private byte[] registrationCertificate;

	public int getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(int vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public byte[] getVehicleImage() {
		return vehicleImage;
	}

	public void setVehicleImage(byte[] vehicleImage) {
		this.vehicleImage = vehicleImage;
	}

	public byte[] getRegistrationCertificate() {
		return registrationCertificate;
	}

	public void setRegistrationCertificate(byte[] registrationCertificate) {
		this.registrationCertificate = registrationCertificate;
	}

	public Vehicle(int vehicleID, String make, String model, int year, String vin, byte[] vehicleImage,
			byte[] registrationCertificate) {
		super();
		this.vehicleID = vehicleID;
		this.make = make;
		this.model = model;
		this.year = year;
		this.vin = vin;
		this.vehicleImage = vehicleImage;
		this.registrationCertificate = registrationCertificate;
	}

	public Vehicle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vehicle(int vehicleID2) {
		this.vehicleID = vehicleID2;
	}
    
}
