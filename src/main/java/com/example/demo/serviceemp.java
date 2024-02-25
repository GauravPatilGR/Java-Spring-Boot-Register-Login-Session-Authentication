package com.example.demo;

public interface serviceemp {
	
	
	public void regsiterdata(empdata s1);
	
	
	empdata findbyemailandpass(String email,String password);
	
	
	empdata findbyiddata(int id);

}
