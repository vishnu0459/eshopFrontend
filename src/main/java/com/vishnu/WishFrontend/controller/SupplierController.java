package com.vishnu.WishFrontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.daoimpl.SupplierDaoImpl;
import com.vishnu.WishBackend.model.Supplier;



@Controller
public class SupplierController {

	@Autowired
	SupplierDaoImpl supplyDao;
	
	
public SupplierController() {

	System.out.println("supplier controller is loaded");
}
	
@RequestMapping(value="/supp",method=RequestMethod.GET)
	
	public ModelAndView supplyFormData()
	{
		 ModelAndView mv=new ModelAndView("AddSupplier");
		 mv.addObject("ButtonName","Add Supplier");
		 mv.addObject("sup",new Supplier());
		 
		 return mv;
		 
				 
	}
	@RequestMapping("/sup_1")
	 public ModelAndView recieveSupplyFormData(@ModelAttribute ("sup") Supplier s)
	    {
			ModelAndView modelAndView=new ModelAndView("Home");
			if(s.getSupplierId()==0)
			{
	        supplyDao.saveSupplier(s);
			}
			else
			{
				supplyDao.editSupplier(s);
			}
	        
	        return modelAndView;
	    }
	@RequestMapping("/showsupply")
	
	public ModelAndView recieveSupplyData()
	{
		
		ModelAndView modelAndView=new ModelAndView("ShowSupplier");
		List<Supplier> l=supplyDao.getSupplierList();
		modelAndView.addObject("showsup",l);
		
	return modelAndView;
	
	}
	
	
	@RequestMapping("/dele")
	public String deleteSupplyData(@RequestParam("sup_id") int supplierId)
	{
		
		Supplier supplier=supplyDao.getSupplier(supplierId);
		supplyDao.deleteSupplier(supplier);
		return "redirect:showsupply";
	}
	
	@RequestMapping("/edits")
	public ModelAndView updateSupplyData(@RequestParam("sup_id") int supplierId)
	{
		Supplier supplier=supplyDao.getSupplier(supplierId);
		ModelAndView modelAndView=new ModelAndView("Supplier");
		modelAndView.addObject("sup",supplier);
		modelAndView.addObject("ButtonName","Update Supplier");
		return modelAndView;
		
	}
	
}
