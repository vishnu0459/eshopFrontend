package com.vishnu.WishFrontend.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.daoimpl.CategoryDaoImpl;
import com.vishnu.WishBackend.daoimpl.ProductDaoImpl;
import com.vishnu.WishBackend.daoimpl.SupplierDaoImpl;
import com.vishnu.WishBackend.model.Category;
import com.vishnu.WishBackend.model.Product;
import com.vishnu.WishBackend.model.Supplier;
import com.vishnu.WishFrontend.utility.FileUtility;




@Controller
public class ProductController {


	
	@Autowired
	ProductDaoImpl dao;
	 
	@Autowired
	CategoryDaoImpl categoryDaoImpl;
	
	@Autowired
	SupplierDaoImpl supplierDao;
	
	public ProductController() {
		System.out.println("Product Controller is Loaded");
	}
	
	
	
	@RequestMapping("/product")
	public ModelAndView retrieveProductFormData()
	{
		ModelAndView modelAndView= new ModelAndView("Product");
		Product product=new Product();
	
		
		
		modelAndView.addObject("pro",product);
		List<Category>  cat_list=categoryDaoImpl.getCategoryList();
		modelAndView.addObject("catList",cat_list);
		List<Supplier> sup_list=supplierDao.getSupplierList();
		modelAndView.addObject("supList",sup_list);
		modelAndView.addObject("ButtonName","Add Product");
		return modelAndView;
		
	}
	@RequestMapping(value="/addpro",method = RequestMethod.POST)
	public String saveProData(@ModelAttribute("pro") Product product)
	
	{
		if(product.getProductId()==0)
		{
			Random random=new Random();
			product.setProductId(random.nextInt(10000));
			dao.saveProduct(product);	
		}
		else
		{
			dao.editProduct(product);
		}
		FileUtility fileUtility=new FileUtility();
		fileUtility.fileupload(product);
		System.out.println("id is====="+product.getProductId());
		
		return "Home";
		
	}
	
	@RequestMapping("/showproduct")
	public ModelAndView displayProduct()
	{
		ModelAndView modelAndView=new ModelAndView("ShowProduct");
		List<Product> list=dao.getProductrList();
		modelAndView.addObject("showproduct",list);
		return modelAndView;
		
	}
	@RequestMapping("/delete")
	public String deleteProductData(@RequestParam("pro_id") int productId)
	{
		
		Product product=dao.getProduct(productId);
		dao.deleteProduct(product);
		FileUtility fileUtility=new FileUtility();
		fileUtility.deleteFile(product);
		
		return "redirect:showproduct";
	}
	
	@RequestMapping("/editpro")
	public ModelAndView updateProductData(@RequestParam("pro_id") int productId)
	{
		Product product=dao.getProduct(productId);
		
		ModelAndView modelAndView=new ModelAndView("Product");
		modelAndView.addObject("pro",product);
		List<Category>  cat_list=categoryDaoImpl.getCategoryList();
		modelAndView.addObject("catList",cat_list);
		List<Supplier> sup_list=supplierDao.getSupplierList();
		modelAndView.addObject("supList",sup_list);
		modelAndView.addObject("ButtonName","Update Product");
		return modelAndView;
		
	}

	@RequestMapping("/cName")
    public ModelAndView getCategoryDataFormProduct(@RequestParam("categoryId") int categoryId) {
        ModelAndView modelAndView = new ModelAndView("UserProducts");
        Category category = categoryDaoImpl.getCategory(categoryId);
        System.out.println("heloo-----"+category.getCategoryName());
        List<Product> productlist = dao.getProductList(category);
        modelAndView.addObject("userProduct", productlist);
        return modelAndView;

    }
	
	
}
