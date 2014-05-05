/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain;

import edu.spcollege.tbk.domain.user.IUserRepository;
import edu.spcollege.tbk.domain.user.User;
import java.util.ArrayList;

/**
 *
 * @author Zhou
 */
public class AuthenticationService {
    private final IUserRepository userRepository;
    private ArrayList<User> loggedInUsers;
    
    // Constructor
    public AuthenticationService(IUserRepository userRepository){
        this.userRepository = userRepository;
        this.loggedInUsers = new ArrayList<>();
    }
    
    // Methods
    public Customer login(String username, String password) throws UserNotFoundException, InvalidPasswordException{
        Customer loginCustomer;
        
        User loginUser = this.userRepository.findByUsername(username);
        if (loginUser == null) throw new UserNotFoundException();
        
        if (loginUser.isValidPassword(password)){
            loginCustomer = loginUser.getCustomer();
            this.loggedInUsers.add(loginUser);
        } else {
            throw new InvalidPasswordException();
        }
        
        return loginCustomer;
    }
    
    private User findLoggedInUser(String username){
        for (User user: this.loggedInUsers){
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }
    
    public boolean isLoggedIn(String username){
       return findLoggedInUser(username) != null;
    }
    
    public void logout(String username){
        User user = findLoggedInUser(username);
        if (user != null){
            this.loggedInUsers.remove(user);
        }
    }
    
}
