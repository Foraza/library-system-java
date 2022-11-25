package com.ifsp.entities;

import java.util.Date;
import java.util.List;

import com.ifsp.interfaces.Listable;

public class Order implements Listable {
	private int id;
	private int clId;
	private Date date;
	private String address;
	private String payment;
	private Double finalPrice;
	private List<Book> books;
	
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
	public Double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBookIds(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		books.add(book);
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
