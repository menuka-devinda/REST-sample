package com.menudev.api.domain;

/**
 * User class is following builder pattern to create the immutable objects
 * For 3 fields we can use constructor initialization too.
 * 
 * @author menuk
 *
 */
public class User {
	
	private  Integer id;
	private  String email;
	private  String name;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	
	
}
