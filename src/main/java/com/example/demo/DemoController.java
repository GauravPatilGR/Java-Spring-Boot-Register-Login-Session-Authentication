package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {
	
	
	@Autowired
	serviceemp t1;
	
	
	
	@RequestMapping("/")
	public String register()
	{
		
		
		return "reg";
	}
	
	@PostMapping("/data")
	public String getdata(@ModelAttribute ("c1") empdata c1)
	{
		
		if(c1.getPassword().equals(c1.getCpassword()))
		{
			t1.regsiterdata(c1);
		}
		
		
		return "login";
	}
	
	@RequestMapping("/login")
	public String logindata() {
		
		
		
		
		return "login";
	}
	
	
	//login Mapping
	@PostMapping("/datach")
	public String checklogin(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession h1)
	{
		
	empdata x =t1.findbyemailandpass(email, password);
	
	h1.setAttribute("username", x.getUsername());
	h1.setAttribute("email", x.getEmail());
	h1.setAttribute("location", x.getLocation());
	h1.setAttribute("password", x.getPassword());
	
	if(x==null)
	{
		return "login";
	}
		
		
		return "dash";
	}
	
	
	@RequestMapping("/dash")
	public String dash(HttpSession h1)
	{
		
	String a=	(String) h1.getAttribute("email");
	
	if(a==null)
	{
		return "login";
	}
		
		return "dash";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession h1)
	{
		h1.invalidate();
		
		return "login";
	}
	
	

}
