package com.epi.pfa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Produit;
import com.epi.pfa.service.Panier;
import com.epi.pfa.service.ProduitService;

@RestController
public class PanierController 
{
	@Autowired
	private ProduitService produitService;
	
	@RequestMapping( value= "/ajouterAuPanier/{id}", method = RequestMethod.GET )
	public void ajouterAuPanier(@PathVariable("id") Long id, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String beforeURL = request.getHeader("referer");
		Produit produit = produitService.getProduit(id);
		Panier panier = (Panier) session.getAttribute("panier");
		panier.ajouterAuPanier(produit, 1);
		
		int i = (Integer) session.getAttribute("compteur");
		i += 1;	
		session.setAttribute("compteur", i);
		response.sendRedirect(beforeURL);
	}
	
	@RequestMapping( value="/maPanier", method = RequestMethod.GET )
	public ModelAndView consulterPanier()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("panier");
		
		return modelAndView;
	}
}
