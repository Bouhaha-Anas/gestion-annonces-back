package com.epi.pfa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Compte;
import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.service.CompteService;
import com.epi.pfa.service.EntrepreneurService;

public class ProfilEntrepreneurController 
{
	
	@Autowired
	EntrepreneurService entrepreneurService;
	
	@Autowired
	CompteService compteService;
	
	@RequestMapping( value= "/profilEntrepreneur", method= RequestMethod.GET )
	public ModelAndView profilEntrepreneur()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Entrepreneur entrepreneur= entrepreneurService.findOneByCompte(compte);
		
		modelAndView.addObject("entrepreneur", entrepreneur);
		modelAndView.setViewName("profilEntrepreneur");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/profilEntrepreneur", method= RequestMethod.PUT )
	public ModelAndView modifierProfilEntrepreneur(@Valid Entrepreneur entrepreneur)
	{
		ModelAndView modelAndView = new ModelAndView();
	
		entrepreneurService.updateEntrepreneur(entrepreneur);
		
		String successMessage = "Vos informations sont mises à jour avec succés";
		modelAndView.addObject("successMessage", successMessage );		
		modelAndView.setViewName("profilEntrepreneur");
		
		return modelAndView;
	}
}
