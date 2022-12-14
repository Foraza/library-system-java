package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.OrderDAO;

@Service
public class OrderService extends GeneralService{
	
	public OrderService() {
		super(new OrderDAO());
	}
}
