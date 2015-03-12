package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
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
}