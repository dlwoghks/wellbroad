package com.wellbroad.action.myroute;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellbroad.action.controller.BaseAction;

public class LoadModalAction implements BaseAction{

	@Override
	public String requestPro(HttpServletRequest request,
							HttpServletResponse resonpse) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("aaa");
		File dirFile=new File("../view/images/tour");
		File []fileList=dirFile.listFiles();
		for(File tempFile : fileList) {
		  if(tempFile.isFile()) {
		    String tempPath=tempFile.getParent();
		    String tempFileName=tempFile.getName();
		    System.out.println("Path="+tempPath);
		    System.out.println("FileName="+tempFileName);
		    /*** Do something withd tempPath and temp FileName ^^; ***/
		  }
		}
		
		
		return "loadmodal.jsp";
	}

}
