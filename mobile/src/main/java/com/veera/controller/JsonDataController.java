package com.veera.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.veera.back.daointer.ProductDAO;
import com.veera.back.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsonDataController {
	
	private static Logger log=LoggerFactory.getLogger(JsonDataController.class);

	@Autowired
	private ProductDAO productDAO;
	

/*	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsList() {		
		return productDAO.list();
				
	}	*/
	
	
	@RequestMapping("/all/products")
	@ResponseBody
	public List<Product> getAllProducts() {
		
		log.info("hello i am from veeru");
		
		return productDAO.listActiveProducts();
				
	}
	
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAlltheProudctsForAdmin(){
		
		return productDAO.list();
		
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable int id) {
		
		return productDAO.listActiveProductsByCategory(id);
				
	}
	
	
	@RequestMapping("/mv/products")
	@ResponseBody
	public List<Product> getMostViewedProducts() {		
		return productDAO.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	@ResponseBody
	public List<Product> getMostPurchasedProducts() {		
		return productDAO.getProductsByParam("purchases", 5);				
	}
	
	
	
	
}
