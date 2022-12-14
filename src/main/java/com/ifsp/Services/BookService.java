package com.ifsp.Services;

import org.springframework.stereotype.Service;

import com.ifsp.connections.BookDAO;

@Service
public class BookService extends GeneralService {

	public BookService() {
		super(new BookDAO());
	}
}
