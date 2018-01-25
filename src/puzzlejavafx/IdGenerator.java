/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlejavafx;

/**
 *
 * @author tibor.wekerle
 */
public class IdGenerator {
    static int Id;
    
    static int getNewId(){
        IdGenerator.Id++;
        return IdGenerator.Id;
    }
}
