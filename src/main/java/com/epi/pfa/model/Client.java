package com.epi.pfa.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity( name="clients" )
public class Client implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	public Long id;
	private String nom;
	private String prenom; 
	private String telephone;
	
	@Column( unique=true )
	private String adresseMail;
	private String adresse;
	private Ville ville;
	
	@Lob
    private byte[] image;
	
	@OneToOne( cascade = {CascadeType.ALL} , orphanRemoval = true )
	@JoinColumn
	private Compte compte;
	
	@OneToMany( cascade=CascadeType.ALL, mappedBy = "client" )
	private List<Contact> contacts;
	
	@OneToMany( mappedBy="client" )
	private List<Commande> commandes;
	
	@OneToMany( mappedBy="client" )
	private List<Recommandation> recommandations;
	
	public Client()
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<Commande> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}

	public List<Recommandation> getRecommandations() {
		return recommandations;
	}

	public void setRecommandations(List<Recommandation> recommandations) {
		this.recommandations = recommandations;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	
	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone
				+ ", adresseMail=" + adresseMail + ", adresse=" + adresse + ", ville=" + ville + ", image="
				+ Arrays.toString(image) + ", compte=" + compte + ", contacts=" + contacts + ", commandes=" + commandes
				+ ", recommandations=" + recommandations + "]";
	}
}
