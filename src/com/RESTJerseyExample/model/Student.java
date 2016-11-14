package com.RESTJerseyExample.model;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Student")
public class Student {
	public int phone;
	public int id;
	public String name;
	public String address;
	
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Student [phone=" + phone + ", id=" + id + ", name=" + name
				+ ", address=" + address + "]";
	}
	
	}
