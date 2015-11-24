package models;
 
import java.util.*;
import javax.persistence.*;

import controllers.Security;
 
import play.db.jpa.*;
import utils.NameGenerator;
 
@Entity
public class User extends Model {
	
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;
    
    
    public User(String email, String password) {
    	this(email, password, email, true);
    }
    
    public User(String email, String password, String fullname, Boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }
    
  //TODO Security issue  
	public static void prePopuplate() {
		new User("alexk","testk").create();
		new User("alexz","textz").create();
	}
    public static User connect(String email, String password) {
        return find("byEmailAndPassword", email, password).first();
    }
    
    public static User createRandomUser(){
    	NameGenerator ng = new NameGenerator();
    	String userName = null;
    	
    	while (userName == null || find("byEmail", userName).first() != null){
    		userName = ng.getName() + "@email.fake";
    	}
    	
    	User u = new User(userName, null);    	
    	u.create();
    	return u;
    }
}