package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.PublisherDAO;

@Service
public class PublisherService extends GeneralService{
	
	public PublisherService() {
		super(new PublisherDAO());
	}
	
}
