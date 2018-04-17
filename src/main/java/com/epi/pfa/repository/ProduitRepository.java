package com.epi.pfa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epi.pfa.model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>
{

}
