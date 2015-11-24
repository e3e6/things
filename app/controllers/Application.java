package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;


public class Application extends CRUD {

	@Before(priority = 1)
    public static void testData() {
		
		List<User> me = User.find("email = ?", "alexz").fetch();
		

		if(me.isEmpty()) {
			User.prePopuplate();	
			
			User u = (User) User.findAll().iterator().next();

			if(u != null){
				Task t1 = new Task(u, "Some Task", "Test Data 1");
					 t1.create();
					 
				Task t2 = new Task(u, "Second Some Task", "Test data 2");
					 t2.create();
			}
			
		}
	}	
	
	
	public static void index() {
//        Task frontPost = Task.find("order by createdOn desc").first();
        
        
//        List<Task> taskList = Task.findAll();
        

        if(Security.isConnected()){
        	String username = Security.connected(); //email used as username
        	
        	User user = User.find("byEmail", username).first();
        	
        	List<Task> taskList = Task.find(
                    "byCreatedBy"
                , user).fetch();
        	
        	String fullname = user.fullname;
        	
        	render(/*frontPost, */username, fullname, taskList);
        } else {
        	render(/*frontPost, */Collections.emptyList());
        }
        
    }
	
	public static void addThing(String title) {
		
	    User u = (User) User.find("byEmail", Security.connected()).first();
	    
	    if(u == null){
	    	//TODO Lazy registration
	    	u = User.createRandomUser();
	    	session.put("username", u.email);
	    }
	    
	    if (u != null){
		    new Task(u, title, "Task's Content").create();
	    }
	    
	    index();
	}

}