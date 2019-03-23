package com.veera.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class fileUtil {
	
	private static final String ABS_PATH="C:\\Users\\localadmin\\git\\veera\\mobile\\src\\main\\webapp\\asserts\\images\\";
	private static String REAL_PATH="null";
	public static void uploadFile(HttpServletRequest req, MultipartFile file, String code) {
		
		REAL_PATH=req.getSession().getServletContext().getRealPath("/asserts/images/");
		
		if(!new File(REAL_PATH).exists()){
			
			new File(REAL_PATH).mkdir();
		}
		
		if(!new File(ABS_PATH).exists()){
			
			new File(ABS_PATH).mkdir();
		}
		
		try{
						//serve loading
			file.transferTo(new File(REAL_PATH+code+".jpg"));
			file.transferTo(new File(ABS_PATH+code+".jpg"));
			
		}
		catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
		}
		
	}

}
