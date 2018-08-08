package com.wellbroad.action.controller;

//FileInputStream
import java.io.FileInputStream;
import java.io.IOException;
//Map,Properties
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.StringUtils;





public class ControllerAction 
                     extends HttpServlet {
	
  //��ɾ�� ��ɾ� ó��Ŭ������ ������ ����
  private Map commandMap = new HashMap();
  
	//������ ����� ������ �ʱ�ȭ �۾�->������
  public void init(ServletConfig config) 
                  throws ServletException {
  	
//��ο� �´� CommandPro.properties������ �ҷ���
  String props = config.getInitParameter("propertyConfig");
  System.out.println("�ҷ��°��="+props);

//��ɾ�� ó��Ŭ������ ���������� ������
//Properties��ü ����
  Properties pr = new Properties();
  FileInputStream f = null;//���Ϻҷ��ö� 
  
      try {
         //CommandPro.properties������ ������ �о��
      	f=new FileInputStream(props);
         
      	//������ ������ Properties�� ����
      	pr.load(f);
      	
      }catch(IOException e){
        throw new ServletException(e);
      }finally{
      if(f!=null) try{f.close();}catch(IOException ex){}	
      }
      	
   //��ü�� �ϳ��� ������ �� ��ü������ Properties
   //��ü�� ����� ��ü�� ����
   Iterator keyiter = pr.keySet().iterator();
   
   while(keyiter.hasNext()){
     //��û�� ��ɾ ���ϱ�����
     String command = (String)keyiter.next();
     System.out.println("command="+command);
     //��û�� ��ɾ�(Ű)�� �ش��ϴ� Ŭ�������� ����
     String className=pr.getProperty(command);
     System.out.println("className="+className);
     
     try{
     //�� Ŭ������ ��ü�� ���������� �޸𸮿� �ε�
     Class commandClass = Class.forName(className);
     System.out.println("commandClass="+commandClass);
     Object commandInstance = commandClass.newInstance();
     System.out.println("commandInstance="+commandInstance);
    
     //Map��ü commandMap�� ����
     commandMap.put(command, commandInstance);
     System.out.println("commandMap="+commandMap);
     
          } catch (ClassNotFoundException e) {
              throw new ServletException(e);
          } catch (InstantiationException e) {
              throw new ServletException(e);
          } catch (IllegalAccessException e) {
              throw new ServletException(e);
          }
      }//while
  }

  public void doGet(//get����� ���� �޼ҵ�
                   HttpServletRequest request, 
                   HttpServletResponse response)
  throws ServletException, IOException {
  	    requestPro(request,response);
  }

  protected void doPost(//post����� ���� �޼ҵ�
                   HttpServletRequest request, 
                   HttpServletResponse response)
  throws ServletException, IOException {
  	    requestPro(request,response);
  }

  //������� ��û�� �м��ؼ� �ش� �۾��� ó��
  private void requestPro(HttpServletRequest request,
  		                HttpServletResponse response) 
  throws ServletException, IOException {
  	String view = null; //��û��ɾ ���� �̵��� ������ ����    	
  	BaseAction com=null;
  	/* ��� �ڽ�Ŭ������ ��ü�� �θ������� ����ȯ ����
  	  /list.do=action.ListAction��ü 
  	  CommandAction com=new ListAction(); �� ������ ��
  	 */
  	try {
  		String command = request.getRequestURI(); //��û��ɾ� �м� => �̵��� �������� ����
  		System.out.println("requestPro() request.getRequestURI() :"+command); //ServletBoard/board/boardList.do
  		
  		//��û��ɾ�� ��������Ʈ�� ���̿� ��ġ�ϴ� ��ġ�� ������ؼ� 
  		System.out.println("requestPro() request.getContextPath() :"+request.getContextPath()); //=> /ServletBoard
  		if(command.indexOf(request.getContextPath())==0) {
  			command=command.substring(request.getContextPath().length()); //  /tour/tourname.do
  		  
//  			if(command.indexOf("/",1) != -1) {
//  				
//  				command=command.substring(command.indexOf("/",1));
//  			}
  			command=command.substring(command.indexOf("/",1)); //  /tourname.do
  			System.out.println("requestPro() command :"+command); //=> /board/boardList.do
  		}
  		

  		
  		//��û��ɾ� /boardList.do => action.ListAction��ü
  		com=(BaseAction) commandMap.get(command); //get<->put
  		System.out.println("requestPro() com :"+com);
  		view = com.requestPro(request, response);
  		System.out.println("requestPro() view :"+view);
  		
  		
  		if (command.equals("/cityname.do") || command.equals("/tourname.do")) {
			return;
		} else {
			//������ ��û��ɾ �ش��ϴ� view�� �̵�(forward)
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request,response);
	  
		}
  		
  		
  		
  	}catch (Throwable e) {
  		throw new ServletException(e); //== e.printStackTrace();
	}
  	
  	
  }	
}

