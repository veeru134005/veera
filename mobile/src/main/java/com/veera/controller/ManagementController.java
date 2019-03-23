                  package com.veera.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.veera.back.daointer.DaoInter;
import com.veera.back.daointer.ProductDAO;
import com.veera.back.dto.Catagory;
import com.veera.back.dto.Product;
import com.veera.util.fileUtil;
import com.veera.validator.ProductValidation;

@Controller
@RequestMapping(value="/manage")
public class ManagementController {
	
	private static Logger log=LoggerFactory.getLogger(ManagementController.class);
	
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
		log.info("Data is "+nproudct);
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
	public String handleProductsubmittion(@Valid @ModelAttribute("product") Product product1,BindingResult result, Model model,HttpServletRequest req){
		
		//creating the a new product records

		
		new ProductValidation().validate(product1, result);

		
		if(result.hasErrors()){
			
			
			model.addAttribute("title","Product Management");
			model.addAttribute("userClickMangeProudcts",true);
			
			return "page";
			
		}
		if(product1.getId()==0){product.add(product1);}
		else{product.update(product1);}
		
	if(!product1.getFile().getOriginalFilename().equals("")){
		
		fileUtil.uploadFile(req,product1.getFile(),product1.getCode());
		
	}
		return "redirect:/manage/products?success=product";
		
		
	}
	
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	@ResponseBody
	public String productActivation(@PathVariable int id){
		
		//fetching the data from database
		Product pro=product.get(id);
		
		boolean isactive=pro.isActive();
		//based on it activation and deactivation the product
		pro.setActive(!isactive);
		//updating the product
		product.update(pro);
		System.out.println("data comes into the methods"+isactive);
		
		return (isactive)? "Your product Has deactivated with id"+pro.getId():
							"Your product Has activated with id"+pro.getId();
	}
	
	
	@RequestMapping(value="/{id}/product",method=RequestMethod.GET)
	public ModelAndView productEdite(@PathVariable int id){
		
		ModelAndView mv=new ModelAndView("page");
		mv.addObject("title","Product Management");
		mv.addObject("userClickMangeProudcts",true);	
		Product nproudct=product.get(id);
		mv.addObject("product",nproudct);
		
	return mv;
	}
	
	
	
}
