package com.vishnu.WishFrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.daoimpl.CategoryDaoImpl;
import com.vishnu.WishBackend.model.Category;







@Controller
public class CatergoryController {
	
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	
	public CatergoryController()
	{
		System.out.println("Category controller is loaded");
	}
	
	
	
	@RequestMapping(value="/category", method = RequestMethod.GET)
    public ModelAndView goToCategoryForm()
    {
        ModelAndView  mv=new ModelAndView("AddCategory");
        
        mv.addObject("cat",new Category());
       // mv.addObject("buttonName","AddCategory");
        
        return  mv;
    }
	  
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public String recieveCategoryFormData(@ModelAttribute ("cat") Category ca)
    {
		
        System.out.println(ca.getCategoryName());
        System.out.println(ca.getCategoryDiscription());
        if(ca.getCatergoryId()==0)  
        {
        categoryDaoImpl.saveCategory(ca);
        }
        else
        {
        	categoryDaoImpl.editCategory(ca);	
        }
        
        return "Home";
    }

	@RequestMapping("/show")
	public ModelAndView receiveCategoryData()
	{
	  ModelAndView mv=new ModelAndView("ShowCategory");
	List<Category>  li=categoryDaoImpl.getCategoryList();
	mv.addObject("showcat",li);
	  return mv;
		
		
	}
	@RequestMapping("/del")
	public String deleteCategory(@RequestParam("cat_id") int catid)
	{
		
		Category category=categoryDaoImpl.getCategory(catid);
		categoryDaoImpl.deleteCategory(category);
		return "redirect:show";
		
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView updateCatData(@RequestParam("cat_id") int catid) 
	{
		Category category=categoryDaoImpl.getCategory(catid);
		
		ModelAndView modelAndView = new ModelAndView("AddCategory");
		modelAndView.addObject("cat",category);
		 modelAndView.addObject("buttonName","UpdateCategory");
		
		return modelAndView;
		
	}

	
}

