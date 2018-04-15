package com.epi.pfa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class RecommandationPrimaryKey implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long idClient;
	private Long idCategorie;

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Long getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	
	@Override
    public boolean equals(Object o) 
	{
        if (this == o) 
        	return true;
        if (o == null || getClass() != o.getClass()) 
        	return false;

        RecommandationPrimaryKey that = (RecommandationPrimaryKey) o;

        if (!idClient.equals(that.idClient)) return false;
        return idCategorie.equals(that.idCategorie);
    }

    @Override
    public int hashCode() 
    {
        int result = idClient.hashCode();
        result = 31 * result + idCategorie.hashCode();
        return result;
    }

}
