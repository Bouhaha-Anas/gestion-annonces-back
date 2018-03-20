package com.epi.pfa.repository;

import org.springframework.data.repository.CrudRepository;

import com.epi.pfa.model.Compte;

public interface CompteRepository extends CrudRepository<Compte , Long>
{
	
}
