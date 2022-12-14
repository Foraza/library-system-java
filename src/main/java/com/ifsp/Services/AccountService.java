package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.AccountDAO;

@Service
public class AccountService extends GeneralService{
	
	public AccountService() {
		super(new AccountDAO());
	}
}
