package com.revature.pojo;

public class Super {
	private Integer id;
	private String alias;
	private Integer alignment;
	private String firstname;
	private String lastname;
	
	public Super() {};
	
	public Super(String alias, String firstname, String lastname, Integer alignment) {
		this.id = null;
		this.alias = alias;
		this.alignment = alignment;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public Super(Integer id, String alias, String firstname, String lastname, Integer alignment) {
		this.id = id;
		this.alias = alias;
		this.alignment = alignment;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public int getID() {
		return this.id;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public void setAlignment(Integer align) {
		this.alignment = align;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getAlias() {
		return this.alias;
	}
	public int getAlignment() {
		return this.alignment;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public String getLastname() {
		return this.lastname;
	}
}
