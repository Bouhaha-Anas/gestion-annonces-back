package com.epi.pfa;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtilities 
{
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static
	{
		try 
		{
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(com.epi.pfa.model.Client.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Categorie.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Compte.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Commande.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Contact.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Entrepreneur.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Recommandation.class);
			configuration.addAnnotatedClass(com.epi.pfa.model.Produit.class);
			
			configuration.configure();
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
		}
		catch (HibernateException e) 
		{
			StandardServiceRegistryBuilder.destroy(serviceRegistry);
			e.printStackTrace();
		}
	}	
	
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
}
