package com.srienath.restapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "technician")
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int technicianID;
    
    @Lob
    @Column(name="profileImage",length=1000000000)
    private byte[] profileImage;

    private String name;
    private String contactNumber;
    private String email;
    private String skills;
    private int rating;
	public int getTechnicianID() {
		return technicianID;
	}
	public void setTechnicianID(int technicianID) {
		this.technicianID = technicianID;
	}
	public byte[] getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public Technician(int technicianID, byte[] profileImage, String name, String contactNumber, String email,
			String skills, int rating) {
		super();
		this.technicianID = technicianID;
		this.profileImage = profileImage;
		this.name = name;
		this.contactNumber = contactNumber;
		this.email = email;
		this.skills = skills;
		this.rating = rating;
	}
	
	public Technician() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
}
