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
	
	public static int i = 0;
	
	@RequestMapping( value= "/ajouterAuPanier/{id}", method = RequestMethod.GET )
	public void ajouterAuPanier(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException
	{
		String sourceURL = request.getHeader("referer");
		i += 1;
		Panier panier = (Panier) session.getAttribute("panier");	
		Produit produit = produitService.getProduit(id);
		panier.ajouterAuPanier(produit, 1);
		request.setAttribute("compteur", i);
		System.out.println("COMPTEUUUUUUUUUUUR :" +i);
		response.sendRedirect(sourceURL);
	}
	
	@RequestMapping( value="/maPanier", method = RequestMethod.GET )
	public ModelAndView consulterPanier()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("panier");
		
		return modelAndView;
	}
}
