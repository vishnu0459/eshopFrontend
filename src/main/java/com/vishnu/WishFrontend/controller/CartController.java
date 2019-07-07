package com.vishnu.WishFrontend.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vishnu.WishBackend.dao.ProductDao;
import com.vishnu.WishBackend.daoimpl.CartDaoImpl;
import com.vishnu.WishBackend.daoimpl.ProductDaoImpl;
import com.vishnu.WishBackend.model.Cart;
import com.vishnu.WishBackend.model.Product;




@Controller
public class CartController {
	
	@Autowired
	CartDaoImpl cartDao;
	
	@Autowired
	ProductDao prodImpl;

	
//	@RequestMapping("/cartProduct")
//	public String cartDetails(@RequestParam("pro_id") int productId)
//	{
//		System.out.println("pro---------id"+productId);
//		Product product=proDao.getProduct(productId);
//		cartDao.saveCartDetails(product);
//		
//		return "ProductDetails";
//	}
	
	public CartController() {
		System.out.println("cart controller");
	}
	@PostMapping("/cartProductss")
	public ModelAndView cartProDetails(@ModelAttribute("cart") Cart cart,HttpSession httpSession)
	{
		ModelAndView modelAndView=new ModelAndView("UserHome");
		String userName=(String)httpSession.getAttribute("username");
		cart.setUserName(userName);
		cart.setCartId(cart.getCartId());
				int productid=cart.getProductId();
			Product product=prodImpl.getProduct(productid);
			if(cart.getCartId()==0)
			{
			cartDao.saveCart(cart);
			}
			else 
			{
				 cartDao.editCart(cart);
	   	    }
			
			return modelAndView;
		
	}
	@RequestMapping("/cartDisplay")
	public ModelAndView displayCart(HttpSession httpsession)
	{
		String uname=(String)httpsession.getAttribute("username");
		List<Cart> list=cartDao.getCartList(uname);
		System.out.println("----"+list);
		ModelAndView modelAndView=new ModelAndView("ViewCart");
		modelAndView.addObject("cartlist",list);
		return modelAndView;
		
	}
	@RequestMapping("/deleteCartProduct")
	public String toDeleteCartProduct(@RequestParam("cart_id") int cartId)
	{
		Cart cart= cartDao.getCart(cartId);
		cartDao.deleteCart(cart);
		
		return "redirect:cartDisplay";
		
	}
	@RequestMapping("/editCartProduct")
	public ModelAndView toEditCartProduct(@RequestParam("cart_id") int cartId,HttpSession httpSession)
	{
		
		ModelAndView modelAndView= new ModelAndView("ProductDetails");
		 String userName=(String)httpSession.getAttribute("username");
		  Cart cart=cartDao.getCart(cartId);
		  cart.setUserName(userName);
		  int quantity=cart.getQuantity();
		  cart.setQuantity(quantity);
		  int productid=cart.getProductId();
		 Product product= prodImpl.getProduct(productid);
		 modelAndView.addObject("proDetails",product);
		 modelAndView.addObject("cart",cart);
		
		
		return modelAndView;
		
	}
}
