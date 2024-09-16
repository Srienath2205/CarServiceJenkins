package com.srienath.restapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "servicereport")
public class ServiceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "jobID")
    private Job job;

    private String serviceDone;
    private double amountPaid;
    private Date paymentDate;
    private String paymentStatus;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getServiceDone() {
		return serviceDone;
	}
	public void setServiceDone(String serviceDone) {
		this.serviceDone = serviceDone;
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
	
	public ServiceReport(int id, Job job, String serviceDone, double amountPaid, Date paymentDate,
			String paymentStatus) {
		super();
		this.id = id;
		this.job = job;
		this.serviceDone = serviceDone;
		this.amountPaid = amountPaid;
		this.paymentDate = paymentDate;
		this.paymentStatus = paymentStatus;
	}
	
	public ServiceReport() {
		super();
		// TODO Auto-generated constructor stub
	}    
	
}
