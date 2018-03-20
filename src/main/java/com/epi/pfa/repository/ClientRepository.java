package com.epi.pfa.repository;

import org.springframework.data.repository.CrudRepository;

import com.epi.pfa.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>
{

}
