package controllers;

import dao.UserDao;
import models.User;

public class Security extends Secure.Security {

    static boolean authenticate(String username, String password) {
    	 return UserDao.connect(username, password) != null;
    }
    
}