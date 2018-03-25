package com.epi.pfa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.epi.pfa.model.Client;
import com.epi.pfa.service.ClientService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ClientController 
{
	@Autowired
	ClientService clientService;
	
	@RequestMapping( value="/clients", method= RequestMethod.GET)
	public List<Client> getAllClients()
	{	
		return clientService.getAllClients();
	}
	
	@RequestMapping( value="/clients/{id}", method= RequestMethod.GET )
	public Client getClient(@PathVariable Long id)
	{
		return clientService.getClient(id);
	}
	
	@RequestMapping( value="/clients/{id}", method= RequestMethod.DELETE )
	public void deleteClient(@PathVariable Long id)
	{
		clientService.deleteClient(id);
	}
}
