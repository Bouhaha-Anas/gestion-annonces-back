package com.epi.pfa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity( name="commandes" )
public class Commande implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CommandePK commandePK;
	
	private int quantite;
	
	@Temporal( TemporalType.DATE )
	private Date dateCommande;
	
	@ManyToOne
	@JoinColumn( name="idProduit", referencedColumnName="id", insertable=false, updatable=false )
	private Produit produit;
	
	@ManyToOne
	@JoinColumn( name="idClient", referencedColumnName="id", insertable=false, updatable=false )
	private Client client;

	public CommandePK getCommandePK() {
		return commandePK;
	}

	public void setCommandePK(CommandePK commandePK) {
		this.commandePK = commandePK;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
