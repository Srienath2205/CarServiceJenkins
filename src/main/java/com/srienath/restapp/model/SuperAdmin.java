package com.srienath.restapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "superadmin")
public class SuperAdmin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int superAdminID;
	
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(unique = true, nullable = false)
    private String password;

	public int getSuperAdminID() {
		return superAdminID;
	}

	public void setSuperAdminID(int superAdminID) {
		this.superAdminID = superAdminID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public SuperAdmin(int superAdminID, String email, String password) {
		super();
		this.superAdminID = superAdminID;
		this.email = email;
		this.password = password;
	}

	public SuperAdmin() {
		super();
		// TODO Auto-generated constructor stub
	} 	

}
