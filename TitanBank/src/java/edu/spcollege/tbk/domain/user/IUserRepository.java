/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.spcollege.tbk.domain.user;


/**
 *
 * @author Zhou
 */
public interface IUserRepository {
    User findByUsername(String username);
}
