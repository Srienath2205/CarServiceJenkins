package com.srienath.restapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "stockpaymentreport")
public class StockPaymentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String restockRequest;
    private Date dispatchDate;
    private Date deliveryDate;
    private String deliveryStatus;
    private double amountPaid;
    private Date paymentDate;
    private String paymentStatus;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRestockRequest() {
		return restockRequest;
	}
	public void setRestockRequest(String restockRequest) {
		this.restockRequest = restockRequest;
	}
	public Date getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	public StockPaymentReport(int id, String restockRequest, Date dispatchDate, Date deliveryDate,
			String deliveryStatus, double amountPaid, Date paymentDate, String paymentStatus) {
		super();
		this.id = id;
		this.restockRequest = restockRequest;
		this.dispatchDate = dispatchDate;
		this.deliveryDate = deliveryDate;
		this.deliveryStatus = deliveryStatus;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}
	
	public StockPaymentReport() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
