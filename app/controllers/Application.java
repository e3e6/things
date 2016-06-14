package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import dao.UserDao;
import models.*;


public class Application extends CRUD {
	
	public static void index() {
        if(Security.isConnected()){
        	User user = UserDao.current(Security.connected());
        	
        	List<Task> taskList = Task.find("byCreatedBy", user).fetch();

        	render(user, taskList);
        } else {
        	render(Collections.emptyList());
        }
        
    }
	
	public static void profile() {
		if(Security.isConnected()){
			User user = UserDao.current(Security.connected());
        	
        	render(user);
		}
	}
	
	public static void logout() {
		session.clear();
		index();
	}
	
	public static void addThing(String title) {
		
	    User u = (User) User.find("byEmail", Security.connected()).first();
	    if(u == null){
	    	//TODO Lazy registration
	    	u = UserDao.createRandomUser();
	    	
	    	session.put("username", u.email);
	    }
	    
	    if (u != null){
		    new Task(u, title, "Task's Content").create();
	    }
	    
	    index();
	}

}