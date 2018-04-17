package com.epi.pfa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epi.pfa.model.Produit;
import com.epi.pfa.repository.ProduitRepository;

@Service
public class ProduitService 
{
	@Autowired
	private ProduitRepository produitRepository;
	
	public void addProduit(Produit produit)
	{
		produitRepository.save(produit);
	}
}
