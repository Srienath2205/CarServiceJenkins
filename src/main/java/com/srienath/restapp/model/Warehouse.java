package com.srienath.restapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int warehouseID;

    private String location;
    private String warehouseName;
    
    @OneToOne
    @JoinColumn(name = "superAdminID")
    private SuperAdmin superAdmin;
    
	public int getWarehouseID() {
		return warehouseID;
	}
	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	public Warehouse(int warehouseID, String location, String warehouseName) {
		super();
		this.warehouseID = warehouseID;
		this.location = location;
		this.warehouseName = warehouseName;
	}
	
	public Warehouse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Warehouse(int warehouseID2) {
		this.warehouseID = warehouseID2;
	}   
	
}
