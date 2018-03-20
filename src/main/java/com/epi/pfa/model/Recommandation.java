package com.epi.pfa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity( name="recommandations" )
public class Recommandation 
{
	@EmbeddedId
	private RecommandationPK recommandationPK;
	
	@ManyToOne
	@JoinColumn( name="idCategorie", referencedColumnName="id", insertable=false, updatable=false )
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn( name="idClient", referencedColumnName="id", insertable=false, updatable=false )
	private Client client;

	public RecommandationPK getRecommandationPK() {
		return recommandationPK;
	}

	public void setRecommandationPK(RecommandationPK recommandationPK) {
		this.recommandationPK = recommandationPK;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
