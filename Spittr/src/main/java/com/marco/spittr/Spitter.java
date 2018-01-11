package com.marco.spittr;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;


public class Spitter {

	
	private Long id;
	
	@NotNull
	@Size(min=5, max=16, message="{userName.size}")
	private String userName;

	@NotNull
	@Size(min=5, max=25, message="{password.size}")
	private String password;
	
	@NotNull
	@Size(min=2, max=30, message="{firstName.size}")
	private String firstName;
	
	@NotNull
	@Size(min=2, max=30, message="{lastName.size}")
	private String lastName;

	@NotNull
	@Email
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Spitter() {}

	public Spitter(String userName, String password, String firstName, String lastName, String email) {		
		this(null, userName, password, firstName, lastName, email);
	}

	
	
			
	
	public Spitter(Long id, String userName, String password, String firstName, String lastName, String email) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, new String[] {"firstName", "lastName", "userName", "password", "email"});
	}
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, new String[] { "firstName", "lastName", "userName", "password", "email"});
	}
	
	
	
	
}
