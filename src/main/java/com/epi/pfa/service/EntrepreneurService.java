package com.epi.pfa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.repository.EntrepreneurRepository;

@Service
public class EntrepreneurService 
{
	@Autowired
	EntrepreneurRepository entrepreneurRepository;
	
	public void addEntrepreneur(Entrepreneur entrepreneur)
	{
		entrepreneurRepository.save(entrepreneur);
	}

	public List<Entrepreneur> getAllEntrepreneurs() 
	{
		return (List<Entrepreneur>) entrepreneurRepository.findAll();
	}

	public Entrepreneur getEntrepreneur(Long id) 
	{
		return entrepreneurRepository.findOne(id);
	}

	public void updateEntrepreneur(Long id, Entrepreneur entrepreneur) 
	{
		entrepreneurRepository.save(entrepreneur);
	}

	public void deleteEntrepreneur(Long id) 
	{
		entrepreneurRepository.delete(id);
	}

	
}
