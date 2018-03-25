package com.epi.pfa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.service.EntrepreneurService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EntrepreneurController 
{
	@Autowired
	EntrepreneurService entrepreneurService;
	
	@RequestMapping( value="/entrepreneurs", method= RequestMethod.GET )
	public List<Entrepreneur> getAllEntrepreneurs()
	{
		return entrepreneurService.getAllEntrepreneurs();
	}
	
	@RequestMapping( value="/entrepreneurs/{id}", method= RequestMethod.GET )
	public Entrepreneur getEntrepreneur(@PathVariable Long id)
	{
		return entrepreneurService.getEntrepreneur(id);
	}
	
	@RequestMapping( value="/entrepreneurs/{id}", method= RequestMethod.DELETE )
	public void deleteEntrepreneur(@PathVariable Long id)
	{
		entrepreneurService.deleteEntrepreneur(id);
	}
}
