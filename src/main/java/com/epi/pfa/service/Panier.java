package com.epi.pfa.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.springframework.stereotype.Component;
import com.epi.pfa.model.Produit;

@Component
//@Scope( value= WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS )
public class Panier 
{
	public static Map<Produit, Integer> listeCommandes = new HashMap<>();
	
	public Panier()
	{
		
	}
	
	public Map<Produit, Integer> getCommandeListe()
	{
		return listeCommandes;
	}
	
	public Set<Produit> getProduit()
	{
		return listeCommandes.keySet();
	}
	
	public void ajouterAuPanier(Produit produit, int count )
	{
		if( listeCommandes.containsKey(produit) )
		{
			listeCommandes.put(produit, listeCommandes.get(produit) + count );
		}
		else
		{
			listeCommandes.put(produit, count);
		}
	}
	
	public void removeProduit(Produit produit)
	{
		listeCommandes.remove(produit);
	}
	
	public void clearCart()
	{
		listeCommandes.clear();
	}

	@Override
	public String toString() 
	{
		return listeCommandes.toString();
	}
	
	public double getPrixTotal()
	{
		double montant = 0;
		for( Produit produit: listeCommandes.keySet() )
		{
			montant += produit.getPrixApresRed();
		}
		
		return montant;
	}
	
}
