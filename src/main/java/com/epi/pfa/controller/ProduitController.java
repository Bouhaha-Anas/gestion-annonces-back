package com.epi.pfa.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Categorie;
import com.epi.pfa.model.Compte;
import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.model.Produit;
import com.epi.pfa.service.CategorieService;
import com.epi.pfa.service.CompteService;
import com.epi.pfa.service.EntrepreneurService;
import com.epi.pfa.service.ProduitService;

@RestController
public class ProduitController 
{
	@Autowired
	private ProduitService produitService;
	
	@Autowired
	EntrepreneurService entrepreneurService;
	
	@Autowired
	CategorieService categorieService;
	
	@Autowired
	CompteService compteService;
	
	
	@RequestMapping( value="/nouvelleOffre", method = RequestMethod.GET )
	public ModelAndView pageAjoutPromotion()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Produit produit = new Produit();
		
		modelAndView.addObject("produit", produit);
		modelAndView.setViewName("nouvelleOffre");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/nouvelleOffre", method = RequestMethod.POST )
	public ModelAndView ajoutPromotion(Produit produit, HttpServletRequest request)
	{
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Entrepreneur entrepreneur = entrepreneurService.findOneByCompte(compte);
		
		String choixCategorie = request.getParameter("choixCategorie");
		String date = request.getParameter("dateFinS");
		
		Categorie categorie = categorieService.findOneByNom(choixCategorie);
		Date dateFin = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			dateFin = sdf.parse(date);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		
		produit.setEstActive(true);
		produit.setDateFin(dateFin);
		produit.setDateDebut(new Date());
		produit.setCategorie(categorie);
		produit.setEntrepreneur(entrepreneur);
		
		produitService.addProduit(produit);
		
		String successMessage = "Votre promotion est ajouté avec succés.";
		
		modelAndView.addObject("successMessage", successMessage);
		modelAndView.setViewName("nouvelleOffre");
		return modelAndView;
	}
}
