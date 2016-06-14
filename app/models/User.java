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
    
    /**
     * unregistered user constructor
     * @param fullname
     */
    public User(String fullname) {
    	this(fullname, null, fullname, false);
    }
    
    public User(String email, String password) {
    	this(email, password, email, true);
    }
    
    public User(String email, String password, String fullname, Boolean isAdmin) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
    }
}