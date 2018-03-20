package com.epi.pfa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epi.pfa.model.Compte;
import com.epi.pfa.service.CompteService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CompteController 
{
	@Autowired
	CompteService compteService;
	
	@RequestMapping( value="/comptes/addCompte", method= RequestMethod.POST )
	public void addRole(@RequestBody Compte compte)
	{
		compteService.addCompte(compte);
	}
}
