package com.epi.pfa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CommandeController 
{
	@RequestMapping( value="/panier", method= RequestMethod.GET )
	public ModelAndView consulterPanier()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("maPanier");
		return modelAndView;
	}
}
