package com.veera.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.veera.back.dto.Product;

public class ProductValidation  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		Product product=(Product)target;
		
/*		if(!(product.getFile().equals(""))||(product.getFile().equals("images/gif"))){
			
			error.rejectValue("file", null,"please select the file");
		
		}*/
		
		
	}


		
	
	
	

}
