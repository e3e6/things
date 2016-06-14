package dao;

import controllers.Security;
import utils.NameGenerator;
import models.User;

public class UserDao {

  //TODO Security issue  
	public static void prePopuplate() {
		new User("alexk","testk").create();
		new User("alexz","textz").create();
	}
	
    public static User current(String username) {
        return (User) User.find("byEmail", username).first();
    }
	
    public static User connect(String email, String password) {
        return User.find("byEmailAndPassword", email, password).first();
    }
    
    public static User createRandomUser(){
    	NameGenerator ng = new NameGenerator();
    	String userName = null;
    	
    	while (userName == null || User.find("byEmail", userName).first() != null){
    		userName = ng.getName();
    	}
    	
    	User u = new User(userName);
    	return u.create() ? u : null;
    }
}
