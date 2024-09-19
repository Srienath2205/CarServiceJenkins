package com.srienath.restapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partID;

    private String partName;
    
    @Lob
    @Column(name="partImage",length=1000000000)
    private byte[] partImage;
    
    private String description;
    
    private int quantityInStock;
    
    private int reorderLimit;
    
    private double price;

    @ManyToOne
    @JoinColumn(name = "serviceCenterID")
    private ServiceCenter serviceCenter;

    @ManyToOne
    @JoinColumn(name = "superAdminID")
    private SuperAdmin superadmin;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private StockPaymentReport stockPaymentReport;

	public int getPartID() {
		return partID;
	}

	public void setPartID(int partID) {
		this.partID = partID;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public byte[] getPartImage() {
		return partImage;
	}

	public void setPartImage(byte[] partImage) {
		this.partImage = partImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public int getReorderLimit() {
		return reorderLimit;
	}

	public void setReorderLimit(int reorderLimit) {
		this.reorderLimit = reorderLimit;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}

	public SuperAdmin getSuperadmin() {
		return superadmin;
	}

	public void setSuperadmin(SuperAdmin superadmin) {
		this.superadmin = superadmin;
	}

	public StockPaymentReport getStockPaymentReport() {
		return stockPaymentReport;
	}

	public void setStockPaymentReport(StockPaymentReport stockPaymentReport) {
		this.stockPaymentReport = stockPaymentReport;
	}

	public Inventory(int partID, String partName, byte[] partImage, String description, int quantityInStock,
			int reorderLimit, double price, ServiceCenter serviceCenter, SuperAdmin superadmin,
			StockPaymentReport stockPaymentReport) {
		super();
		this.partID = partID;
		this.partName = partName;
		this.partImage = partImage;
		this.description = description;
		this.quantityInStock = quantityInStock;
		this.reorderLimit = reorderLimit;
		this.price = price;
		this.serviceCenter = serviceCenter;
		this.superadmin = superadmin;
		this.stockPaymentReport = stockPaymentReport;
	}

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}	

}
