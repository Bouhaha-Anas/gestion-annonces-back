package com.epi.pfa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Client;
import com.epi.pfa.model.Compte;
import com.epi.pfa.service.ClientService;
import com.epi.pfa.service.CompteService;

@RestController
public class ProfilClientController 
{
	@Autowired
	ClientService clientService;
	
	@Autowired
	CompteService compteService;
	
	@RequestMapping( value= "/profilClient", method= RequestMethod.GET )
	public ModelAndView profilClient()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Client client = clientService.findOneByCompte(compte);
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value= "/profilClient/parametresGeneraux", method= RequestMethod.GET )
	public ModelAndView profilClientParametresGeneraux()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Client client = clientService.findOneByCompte(compte);
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value= "/profilClient/mesCommandes", method= RequestMethod.GET )
	public ModelAndView profilClientMesCommandes()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Client client = clientService.findOneByCompte(compte);
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value= "/profilClient/mesRecommandations", method= RequestMethod.GET )
	public ModelAndView profilClientRecommandations()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Client client = clientService.findOneByCompte(compte);
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value= "/profilClient/mesMassages", method= RequestMethod.GET )
	public ModelAndView profilClientMessages()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		Compte compte = compteService.findOneByLogin(login);
		Client client = clientService.findOneByCompte(compte);
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/profilClient/parametresGeneraux", method= RequestMethod.POST )
	public ModelAndView modifierProfilClient(@Valid Client client)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		clientService.updateClient(client);
		
		String successMessage = "Vos informations sont mises à jour avec succés";
		modelAndView.addObject("successMessage", successMessage );		
		modelAndView.setViewName("profilClient");
		
		return modelAndView;
	}
}
