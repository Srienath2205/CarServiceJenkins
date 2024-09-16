package com.srienath.restapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobID;

    @OneToOne
    @JoinColumn(name = "appointmentID")
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "technicianID")
    private Technician technician;

    private String jobStatus;
    private LocalDateTime jobStartTime;
    private LocalDateTime jobEndTime;
    
	public int getJobID() {
		return jobID;
	}
	public void setJobID(int jobID) {
		this.jobID = jobID;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public Technician getTechnician() {
		return technician;
	}
	public void setTechnician(Technician technician) {
		this.technician = technician;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public LocalDateTime getJobStartTime() {
		return jobStartTime;
	}
	public void setJobStartTime(LocalDateTime jobStartTime) {
		this.jobStartTime = jobStartTime;
	}
	public LocalDateTime getJobEndTime() {
		return jobEndTime;
	}
	public void setJobEndTime(LocalDateTime jobEndTime) {
		this.jobEndTime = jobEndTime;
	}
	
	public Job(int jobID, Appointment appointment, Technician technician, String jobStatus, LocalDateTime jobStartTime,
			LocalDateTime jobEndTime) {
		super();
		this.jobID = jobID;
		this.appointment = appointment;
		this.technician = technician;
		this.jobStatus = jobStatus;
		this.jobStartTime = jobStartTime;
		this.jobEndTime = jobEndTime;
	}
	
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
       
}
