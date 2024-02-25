package com.example.demo;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String getdata(@ModelAttribute ("c1") empdata c1,@RequestParam("profileupload") MultipartFile filename) throws IOException
	{
		
		if(c1.getPassword().equals(c1.getCpassword()))
		{
			
			//file upload
			
			String f=filename.getOriginalFilename();
			
			String path="C:\\Users\\gaura\\eclipse-workspace\\Reg_login_spring_boot\\src\\main\\resources\\static\\images";
			
			BufferedOutputStream bf =new BufferedOutputStream(new FileOutputStream(path+"/"+f));
			
			byte b []=filename.getBytes();
			
			bf.write(b);
			
			bf.close();
			
			c1.setProfilephoto(f);
			
	
			
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
	h1.setAttribute("profile", x.getProfilephoto());
	h1.setAttribute("about", x.getAbout());
	h1.setAttribute("id", x.getId());
	

	
	if(x==null)
	{
		return "redirect:/login";
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
	
	//edit
	
	@GetMapping("/edit/{id}")
	public String editdata(@PathVariable int id,ModelMap m)
	{
		
	empdata e	=t1.findbyiddata(id);
	
	m.addAttribute("kk",e);
		
		
		return "edit";
	}
	
	//Update
	
	@PostMapping("/dataupdate")
	public String updatedata(@ModelAttribute ("c1") empdata c1,@RequestParam ("profileupload") MultipartFile filename) throws IOException
	{
		
		//file upload
		
		String f=filename.getOriginalFilename();
		
		String path="C:\\Users\\gaura\\eclipse-workspace\\Reg_login_spring_boot\\src\\main\\resources\\static\\images";
		
		BufferedOutputStream bf =new BufferedOutputStream(new FileOutputStream(path+"/"+f));
		
		byte b []=filename.getBytes();
		
		bf.write(b);
		
		bf.close();
		
		c1.setProfilephoto(f);
		
		
		empdata e1= new empdata();
		
		e1.setId(c1.getId());
		e1.setUsername(c1.getUsername());
		e1.setEmail(c1.getEmail());
		e1.setProfilephoto(c1.getProfilephoto());
		e1.setLocation(c1.getLocation());
		e1.setAbout(c1.getAbout());
		e1.setPassword(c1.getPassword());
		e1.setCpassword(c1.getCpassword());
		
		t1.regsiterdata(e1);
		
		
		return "redirect:/logout";
	}
	
	
	
	

	
	@RequestMapping("/logout")
	public String logout(HttpSession h1)
	{
	 
		h1.invalidate();
		
		return "login";
	}
	
	
	
	

}
