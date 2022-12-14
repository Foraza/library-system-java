package com.ifsp.entities;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.ifsp.interfaces.Listable;

public class Order implements Listable {
	private int id;
	private int clId;
	private Client client;
	private Date date;
	private String address;
	private String payment;
	private List<OrderItem> items = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClId() {
		return clId;
	}
	public void setClId(int clId) {
		this.clId = clId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public void addBook(OrderItem item) {
		items.add(item);
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
