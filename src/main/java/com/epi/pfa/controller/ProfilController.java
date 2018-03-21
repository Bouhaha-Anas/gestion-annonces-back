package com.epi.pfa.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProfilController 
{
	@RequestMapping( value= "/profil", method= RequestMethod.GET )
	public ModelAndView profil()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("profil");
		
		return modelAndView;
	}
}
