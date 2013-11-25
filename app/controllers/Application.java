package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	@Before(priority = 1)
    public static void testData() {
		
		List<User> me = User.find("email = ?", "alexz").fetch();
		

		if(me.isEmpty()) {
			User.prePopuplate();	
			
			User u = (User) User.findAll().iterator().next();

			if(u != null){
				Task t1 = new Task(u, "Some Task", "Task's Content");
					 t1.create();
					 
				Task t2 = new Task(u, "Second Some Task", "Second Task's Content");
					 t2.create();
			}
			
		}
	}	
	
	
	public static void index() {
//        Task frontPost = Task.find("order by createdOn desc").first();
//        List<Task> taskList = Task.find(
//            "order by createdOn desc"
//        ).from(1).fetch(10);
        
        List<Task> taskList = Task.findAll();
        List<User> userList = User.findAll();
        
        render(/*frontPost, */userList, taskList);
    }
	
	public static void addThing(String title) {
	    User u = (User) User.findAll().iterator().next();
	    if (u != null){
		    Task t1 = new Task(u, title, "Task's Content");
			 	 t1.create();
	    }
	    
	    index();
	}

}