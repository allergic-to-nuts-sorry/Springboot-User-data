package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.View;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import com.example.demo.model.User;


@Controller
public class IndexController {
	
	public static String fileName="C://file/output.csv";
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@PostMapping("/register")
	public String userRegistration(@ModelAttribute User user) {
		System.out.println(user.toString());
		
		if(!validateUser(user)) {
			System.out.println("Validation failed");
			return "emailerror";
		}

		try {
		writeTofile(user);
		}
		catch(Exception e) {
			System.out.println("Exception occured");
		}
		
		return "index";
	}

	private boolean validateUser(User user){
		
		boolean validationStatus=true;
	      try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
	        {
	            String line;
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	                StringTokenizer st1 = new StringTokenizer(line, ",");
	                while (st1.hasMoreTokens()) {
	                	String token=st1.nextToken();
	                	System.out.println(token);
	                	
	                	String[] value=token.split(":");

	                	if (value[0].equalsIgnoreCase("Email")) {
	                		String email=value[1];
	                		
	                		if (user.getEmail().equalsIgnoreCase(email))
	                			validationStatus=false;  
	                	}

	                	
	                }	
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return validationStatus;
	}

	public void writeTofile(User user) throws IOException {
		
        BufferedWriter out = new BufferedWriter(
                new FileWriter(fileName, true));
 
        StringBuffer sb = new StringBuffer();
        
        sb=sb.append("Role:"+user.getRole()
        		+ ",FirstName:"+user.getFname()
        		+ ",LastName:"+user.getLname()
        		+ ",Email:"+user.getEmail()
        		+ ",Address:"+ user.getAddress()+ "\n");
        
            // Writing on output stream
            out.write(sb.toString());
            // Closing the connection
            out.close();
  }
	
	@GetMapping("/getAll")
	public String getAll(Model model) {
		       
	
        List<User> userList=new ArrayList<User>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
        	
        	
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                StringTokenizer st1 = new StringTokenizer(line, ",");
            	User user=new User();
                while (st1.hasMoreTokens()) {
                	String token=st1.nextToken();
                	System.out.println(token);
                	
                	String[] value=token.split(":");

                	if(value[0].equalsIgnoreCase("role"))
                		user.setRole(value[1]);
                	if (value[0].equalsIgnoreCase("FirstName"))
                		user.setFname(value[1]);
                	if (value[0].equalsIgnoreCase("LastName"))
                		user.setLname(value[1]);
                	if (value[0].equalsIgnoreCase("Email"))
                		user.setEmail(value[1]);
                	if (value[0].equalsIgnoreCase("Address"))
                		user.setAddress(value[1]);
                	
                }
                userList.add(user);
                
                model.addAttribute("allemplist", userList); 	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "allemplist";    
	}
	
	@GetMapping("/getAdminUser")
	public String getAdminUser(Model model) {
		       
	
        List<User> userList=new ArrayList<User>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
        	
        	
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                StringTokenizer st1 = new StringTokenizer(line, ",");
            	User user=new User();
            	if(line.contains("Admin")) {
                while (st1.hasMoreTokens()) {
                	String token=st1.nextToken();
                	System.out.println(token);
                	
                	String[] value=token.split(":");


	                	if(value[0].equalsIgnoreCase("role"))
	                		user.setRole(value[1]);
	                	if (value[0].equalsIgnoreCase("FirstName"))
	                		user.setFname(value[1]);
	                	if (value[0].equalsIgnoreCase("LastName"))
	                		user.setLname(value[1]);
	                	if (value[0].equalsIgnoreCase("Email"))
	                		user.setEmail(value[1]);
	                	if (value[0].equalsIgnoreCase("Address"))
	                		user.setAddress(value[1]);
	                	
                }
                userList.add(user);
                
                model.addAttribute("allemplist", userList);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "allemplist";    
	}
	
	@GetMapping("/getAllUser")
	public String getAllUser(Model model) {
		       
	
        List<User> userList=new ArrayList<User>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
        	
        	
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                StringTokenizer st1 = new StringTokenizer(line, ",");
            	User user=new User();
            	if(line.contains("User")) {
                while (st1.hasMoreTokens()) {
                	String token=st1.nextToken();
                	System.out.println(token);
                	
                	String[] value=token.split(":");


	                	if(value[0].equalsIgnoreCase("role"))
	                		user.setRole(value[1]);
	                	if (value[0].equalsIgnoreCase("FirstName"))
	                		user.setFname(value[1]);
	                	if (value[0].equalsIgnoreCase("LastName"))
	                		user.setLname(value[1]);
	                	if (value[0].equalsIgnoreCase("Email"))
	                		user.setEmail(value[1]);
	                	if (value[0].equalsIgnoreCase("Address"))
	                		user.setAddress(value[1]);
	                	
                }
                userList.add(user);
                
                model.addAttribute("allemplist", userList);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "allemplist";    
	}
}