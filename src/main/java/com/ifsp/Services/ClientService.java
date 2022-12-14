package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.ClientDAO;

@Service
public class ClientService extends GeneralService {

	public ClientService() {
		super(new ClientDAO());
	}
}
