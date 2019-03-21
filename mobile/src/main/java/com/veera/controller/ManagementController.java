                  package com.veera.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.veera.back.daointer.DaoInter;
import com.veera.back.daointer.ProductDAO;
import com.veera.back.dto.Catagory;
import com.veera.back.dto.Product;

@Controller
@RequestMapping(value="/manage")
public class ManagementController {
	
	@Autowired
	private DaoInter category;
	
	@Autowired
	private ProductDAO product;
	
	@RequestMapping( value="/products",method=RequestMethod.GET)
	public ModelAndView showManageproducts(@RequestParam(name="success",required=false)  String success ){
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Product Management");
		mv.addObject("userClickMangeProudcts",true);
		Product nproudct=new Product();
		nproudct.setSupplierId(1);
		nproudct.setActive(true);
		
		if(success!=null){
			
			if(success.equals("product")){
				
				mv.addObject("message", "Product submitted successfully!");
			}
			else if(success.equals("category")){
				mv.addObject("message", "category submitted successfully!");
			}
			
		}
		
		mv.addObject("product",nproudct);
		return mv;
	}

	@ModelAttribute("catagories")
	public List<Catagory> getCategories(){
	
		return category.list()  ; 

	}
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductsubmittion(@Valid @ModelAttribute("product") Product product1,BindingResult result, Model model){
		
		//creating the a new product records
		
		
		if(result.hasErrors()){
			
			
			model.addAttribute("title","Product Management");
			model.addAttribute("userClickMangeProudcts",true);
			
			return "page";
			
		}

		product.add(product1);
		
		return "redirect:/manage/products?success=product";
		
		
	}
	
	
	
	
}
