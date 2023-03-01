package com.yogesh.jwt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="USER_DETAILS")
public class User {
	 	@Id
	    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
	    @GenericGenerator(name = "native",strategy = "native")
	    private int id;
	 	
	 	@Column
	    private String email;
	 	
	 	@Column
	    private String pwd;
	 	
	 	@Column
	    private String role;

	    public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPwd() {
	        return pwd;
	    }

	    public void setPwd(String pwd) {
	        this.pwd = pwd;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
}
