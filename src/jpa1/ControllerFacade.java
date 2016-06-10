/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa1;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author butwhole
 */
public class ControllerFacade {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
    private EntityManager em = emf.createEntityManager();
    
    
    public ProjectUser CreateUser(String userName, String email){
        ProjectUser user = new ProjectUser();
        user.setEmail(email);
        Date date = new Date();
        user.setCreated(date);
        user.setUserName(userName);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return user;
    }
    public List<ProjectUser> FindUsers(String name){
        
        Query q = em.createQuery("SELECT p FROM ProjectUser p WHERE p.userName = :"+name);
        return q.getResultList();
    }
    
    public ProjectUser FindUser(int id){
        return em.find(ProjectUser.class, id);
    }
    public List<ProjectUser> FindUsers(){
        Query q = em.createQuery("SELECT p FROM ProjectUser p");
        return q.getResultList();
        
    }
    
    public void emClose(){
        em.close();
    }
    public Project createProject(String name, String disc){
        Project p = new Project();
        p.setName(name);
        Date date = new Date();
        p.setCreated(date);
        p.setLastModified(date);
        p.setDescription(disc);
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        return p;
    }
    public void assignUser(int userId, int projectId){
        ProjectUser user = em.find(ProjectUser.class, userId);
        Project project = em.find(Project.class, projectId);
        em.getTransaction().begin();
        user.addProject(project);
        project.addProjectUser(user);
        project.setLastModified(new Date());
        em.getTransaction().commit();
    }
    
    public Project findProject(int id){
        return em.find(Project.class, id);
    }

    public void CreateTaskAndAssignToProject(String taskName, int projectId, String disc, double hoursAssigned){
        Task task = new Task();
        task.setName(taskName);
        task.setDesription(disc);
        task.setHoursAssigned(hoursAssigned);
        task.setHoursUsed(0);
        Project p = findProject(projectId);
        em.getTransaction().begin();
        p.addTasks(task);
        p.setLastModified(new Date());
        em.getTransaction().commit();
    }
    
    
}
