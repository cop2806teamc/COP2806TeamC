/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain;

/**
 *
 * @author Zhou
 */
public class InvalidPasswordException extends Exception {
    
    public InvalidPasswordException() {
        super("Error: Invalid Password.");
    }
}
