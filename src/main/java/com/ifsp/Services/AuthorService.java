package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.AuthorDAO;

@Service
public class AuthorService extends GeneralService {

	public AuthorService() {
		super(new AuthorDAO());
	}
}
