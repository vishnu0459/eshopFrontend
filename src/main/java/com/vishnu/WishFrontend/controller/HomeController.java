package com.vishnu.WishFrontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.daoimpl.CategoryDaoImpl;

@Controller

public class HomeController 
{
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	
public HomeController() {
		System.out.println("home Controller");
	}
	
	@RequestMapping("/home")
	public String goToHome()
	{
		return "Home";
	}
	@RequestMapping("/dfg")
	ModelAndView m5()
	{
		ModelAndView modelAndView=new ModelAndView("hello");
		modelAndView.addObject("nm",128);
		return modelAndView;
	}
	@RequestMapping("/bcd")
	ModelAndView a1()
	{
		ModelAndView modelAndView=new ModelAndView("ShowCategory");
		modelAndView.addObject("nm",120);
		return modelAndView;
	}
	@RequestMapping("/about")
	public String aboutUs()
	{
		return "AboutUs";
	}
	@RequestMapping("/contact")
	public String contactUs()
	
	{
		return "ContactUs";
	}
	
	

}
