package com.epi.pfa.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity( name="categories" )
public class Categorie implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;
	private String nom;
	
	@OneToMany( cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "categorie", fetch=FetchType.EAGER )
	private List<Produit> produits;
	
	@OneToMany( cascade=CascadeType.ALL, orphanRemoval = true, mappedBy = "categorie", fetch=FetchType.EAGER )
	private List<Categorie> categories;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "categorie_id" )
	private Categorie categorie;
	
	@OneToMany( mappedBy="categorie" )
	private List<Recommandation> recommandations;
	
	public Categorie()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Recommandation> getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(List<Recommandation> recommandations) {
		this.recommandations = recommandations;
	}
	
	
	
}
