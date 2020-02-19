/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum;

import br.com.caelum.model.Produto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Gustavo
 */
public class TestaCache {
    
    public static void main(String[] args) {
        
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);
        
        EntityManagerFactory emf = ctx.getBean(EntityManagerFactory.class);
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();
        
        Produto produto = em.find(Produto.class, 1);
        Produto produto2 = em2.find(Produto.class, 1);
        
        System.out.println("1 - " + produto.getNome());
        System.out.println("2 - " + produto2.getNome());
        
        em.close();
        
    }
    
}
