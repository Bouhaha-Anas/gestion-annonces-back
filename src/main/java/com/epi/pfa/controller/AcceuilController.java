package com.epi.pfa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Categorie;
import com.epi.pfa.service.CategorieService;

@RestController
public class AcceuilController 
{
	@Autowired
	CategorieService categorieService;
	
	@RequestMapping( value= "/accueil", method = RequestMethod.GET )
	public ModelAndView accueil()
	{
		ModelAndView modelAndView = new ModelAndView();
		List<Categorie> categories = categorieService.findAllCategories();
		
		modelAndView.addObject("categories",categories); 
		modelAndView.setViewName("accueil");
		
		return modelAndView;
	}
}
