package com.epi.pfa.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Client;
import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.service.ClientService;
import com.epi.pfa.service.EntrepreneurService;

@RestController
public class LoginController 
{
	@Autowired
	ClientService clientService;
	
	@Autowired
	EntrepreneurService entrepreneurService;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionClient", method= RequestMethod.GET )
	public ModelAndView inscriptionClient()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Client client = new Client();
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("inscriptionClient");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionClient", method= RequestMethod.POST )
	public ModelAndView addClient(@Valid Client client, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		clientService.addClient(client);
	
		modelAndView.addObject("client", new Client());
		modelAndView.setViewName("inscriptionClient");
		modelAndView.addObject("successMessage", "Votre Inscription est effectuée avec succés");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionEntrepreneur", method= RequestMethod.GET )
	public ModelAndView inscriptionEntrepreneur()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Entrepreneur entrepreneur = new Entrepreneur();
		
		modelAndView.addObject("entrepreneur", entrepreneur);
		modelAndView.setViewName("inscriptionEntrepreneur");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionEntrepreneur", method= RequestMethod.POST )
	public ModelAndView addEntrepreneur(@Valid Entrepreneur entrepreneur, BindingResult bindingResult)
	{
		ModelAndView modelAndView = new ModelAndView();
		
		entrepreneurService.addEntrepreneur(entrepreneur);
		
		modelAndView.addObject("entrepreneur", new Entrepreneur());
		modelAndView.setViewName("inscriptionEntrepreneur");
		modelAndView.addObject("successMessage", "Votre Inscription est effectuée avec succés");
		
		return modelAndView;
	}

}
