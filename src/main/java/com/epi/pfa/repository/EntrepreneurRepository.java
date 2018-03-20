package com.epi.pfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epi.pfa.model.Entrepreneur;

public interface EntrepreneurRepository extends JpaRepository<Entrepreneur, Long>
{

}
