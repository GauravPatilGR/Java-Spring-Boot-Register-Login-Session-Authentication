package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Daoemp implements serviceemp {
	
	
	@Autowired
	Repositoryemp r1;
	
	

	@Override
	public void regsiterdata(empdata s1) {
		
		r1.save(s1);
		
	}



	@Override
	public empdata findbyemailandpass(String email, String password) {
		
		return r1.findByEmailAndPassword(email, password);
	}



	@Override
	public empdata findbyiddata(int id) {
		
		return r1.getById(id);
	}



	



	
}
