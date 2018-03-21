package com.epi.pfa.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.epi.pfa.model.Client;
import com.epi.pfa.repository.ClientRepository;

@Service
public class ClientService 
{
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<Client> getAllClients()
	{
		return (List<Client>) clientRepository.findAll();
	}
	
	public void addClient(Client client)
	{
		//client.getCompte().setMotDePasse(bCryptPasswordEncoder.encode(client.getCompte().getMotDePasse()));
		client.getCompte().setEnabled(true);
		client.getCompte().setRole("CLIENT");
		clientRepository.save(client);
	}
	
	public Client getClient(Long id)
	{
		return clientRepository.findOne(id);
	}

	public void updateClient(Long id, Client client) 
	{
		clientRepository.save(client);
	}
	
	public void deleteClient(Long id) 
	{
		clientRepository.delete(id);
	}
}
