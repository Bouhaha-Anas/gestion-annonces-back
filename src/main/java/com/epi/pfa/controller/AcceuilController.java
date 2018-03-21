package com.epi.pfa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AcceuilController 
{
	@RequestMapping( value= "/accueil", method = RequestMethod.GET )
	public ModelAndView accueil()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("accueil");
		
		return modelAndView;
	}
}
