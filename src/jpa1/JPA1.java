/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa1;

import javax.persistence.Persistence;

/**
 *
 * @author butwhole
 */
public class JPA1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Persistence.generateSchema("PU", null);
        ControllerFacade con = new ControllerFacade();
        con.CreateUser("marco", "dick@dickerdick.com");
        con.createProject("awesomeStuff", "name says it all");
        ProjectUser user = con.FindUser(1);
        Project project = con.findProject(1);
        con.assignUser(user.getId(), project.getId());
        con.CreateTaskAndAssignToProject("yolo", project.getId(), "yololololo", 5);
        con.emClose();
    }
    
}
