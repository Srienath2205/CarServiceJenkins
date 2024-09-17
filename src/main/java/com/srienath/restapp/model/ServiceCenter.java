package com.srienath.restapp.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "servicecenter")
public class ServiceCenter {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceCenterID;
	
    private String serviceCenterName;
    
    @Lob
    @Column(name="serviceCenterImage",length=1000000000)
    private byte[] serviceCenterImage;
    
    private String address;
    
    private String phoneNumber;
    
    private String email;
    
    private String location;
    
    private String description;
    
    private String status;
    
    private Date approvalDate;
        
    @Lob
    @Column(name="businessregistercertificate",length=1000000000)
    private byte[] businessRegistrationCertificate;
    
    @Lob
    @Column(name="insurancedocument",length=1000000000)
    private byte[] insuranceDocument;
    
    @Lob
    @Column(name="owneridentityproof",length=1000000000)
    private byte[] ownerIdentityProof;
    
    @OneToOne
    @JoinColumn(name = "adminID")
    private Admin admin;

	public int getServiceCenterID() {
		return serviceCenterID;
	}

	public void setServiceCenterID(int serviceCenterID) {
		this.serviceCenterID = serviceCenterID;
	}

	public String getServiceCenterName() {
		return serviceCenterName;
	}

	public void setServiceCenterName(String serviceCenterName) {
		this.serviceCenterName = serviceCenterName;
	}

	public byte[] getServiceCenterImage() {
		return serviceCenterImage;
	}

	public void setServiceCenterImage(byte[] serviceCenterImage) {
		this.serviceCenterImage = serviceCenterImage;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public byte[] getBusinessRegistrationCertificate() {
		return businessRegistrationCertificate;
	}

	public void setBusinessRegistrationCertificate(byte[] businessRegistrationCertificate) {
		this.businessRegistrationCertificate = businessRegistrationCertificate;
	}

	public byte[] getInsuranceDocument() {
		return insuranceDocument;
	}

	public void setInsuranceDocument(byte[] insuranceDocument) {
		this.insuranceDocument = insuranceDocument;
	}

	public byte[] getOwnerIdentityProof() {
		return ownerIdentityProof;
	}

	public void setOwnerIdentityProof(byte[] ownerIdentityProof) {
		this.ownerIdentityProof = ownerIdentityProof;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public ServiceCenter(int serviceCenterID, String serviceCenterName, byte[] serviceCenterImage, String address,
			String phoneNumber, String email, String location, String description, String status, Date approvalDate,
			byte[] businessRegistrationCertificate, byte[] insuranceDocument, byte[] ownerIdentityProof, Admin admin) {
		super();
		this.serviceCenterID = serviceCenterID;
		this.serviceCenterName = serviceCenterName;
		this.serviceCenterImage = serviceCenterImage;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.location = location;
		this.description = description;
		this.status = status;
		this.approvalDate = approvalDate;
		this.businessRegistrationCertificate = businessRegistrationCertificate;
		this.insuranceDocument = insuranceDocument;
		this.ownerIdentityProof = ownerIdentityProof;
		this.admin = admin;
	}

	public ServiceCenter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
