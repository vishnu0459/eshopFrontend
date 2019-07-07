package com.vishnu.WishFrontend.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.dao.CartDao;
import com.vishnu.WishBackend.daoimpl.CartDaoImpl;
import com.vishnu.WishBackend.daoimpl.OrderDetailsDaoImpl;
import com.vishnu.WishBackend.daoimpl.ProductDaoImpl;

import com.vishnu.WishBackend.daoimpl.UserDaoImpl;
import com.vishnu.WishBackend.model.Cart;
import com.vishnu.WishBackend.model.Product;
import com.vishnu.WishBackend.model.ShippingAddress;
import com.vishnu.WishBackend.model.UserOrder;
import com.vishnu.WishBackend.model.UserRegister;

@Controller
public class OrderController {
	
	@Autowired
	UserDaoImpl userDao;
	
	@Autowired
	CartDaoImpl cartDao;
	
	@Autowired
	OrderDetailsDaoImpl orderDao;
	
	
	
	@Autowired
	ProductDaoImpl productDao;
	
	
	
	
	

	@RequestMapping("/placeorder")
	public ModelAndView orderpage(HttpSession session)
	{
		
		String email=(String)session.getAttribute("username");
		 System.out.println("emailis $$==="+email);
	    
		 UserRegister user= userDao.getUser(email);
		ModelAndView modelAndView=new ModelAndView("Order");
		ShippingAddress address= new ShippingAddress();
		if(user.getShippingAddress()==null)
		{
			modelAndView.addObject("place",address);
		}
		else
		{
		address=user.getShippingAddress();
		modelAndView.addObject("place",address);
		}
		
		
		return modelAndView;
	}
	@PostMapping("/ordered")
	public ModelAndView ordered(@ModelAttribute("place") ShippingAddress address,HttpSession session)
	{
		ModelAndView modelAndView=new ModelAndView("OrderDetails");
		UserOrder userOrder=new UserOrder();
		String email=(String)session.getAttribute("username");
		 System.out.println(email);
		 UserRegister user= userDao.getUser(email);
		 
		int grandTotal=0;
		List<Cart> list= cartDao.getCartList(email);
		for(Cart cat:list)
		{
			Product product= productDao.getProduct(cat.getProductId());
	    	int stock=product.getStock()-cat.getQuantity();
			product.setStock(stock);
			productDao.editProduct(product);
			grandTotal=grandTotal+cat.getCartTotalPrice();
		}
		 Date date=new Date();
		 //shippingaddDao.saveShippingAddress(address);
		
		
		 user.setShippingAddress(address);
		 userOrder.setUser(user);
		 userOrder.setGrandTotal(grandTotal);
		 userOrder.setShippingAddress(address);   
		 userOrder.setDate(date);
		 orderDao.saveOrderDetails(userOrder);
		 for(Cart cat:list)
			{
				cartDao.deleteCart(cat);
			}
		 
		 modelAndView.addObject("userorder",userOrder);
		 
		return modelAndView;
	}
	
	@RequestMapping("/myorders")
	public ModelAndView totalUserOrders(HttpSession session)
	{
		String email=(String) session.getAttribute("username");
		List<UserOrder> list= orderDao.getMyOrders(email);
		for(UserOrder l:list)
		{
			System.out.println(l.getUserOrderId());
		}
		ModelAndView modelAndView=new ModelAndView("MyOrders");
		modelAndView.addObject("orderlist",list);
		return modelAndView;
	}
	
}
