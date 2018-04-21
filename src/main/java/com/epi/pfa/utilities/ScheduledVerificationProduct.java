package com.epi.pfa.utilities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.epi.pfa.model.Produit;
import com.epi.pfa.service.ProduitService;

@Component
public class ScheduledVerificationProduct 
{
	@Autowired
	private ProduitService produitService;
	
	@Scheduled(cron="0 0 0 * * *")
	public void dateFinProduit()
	{
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);	
		date = calendar.getTime();
		
		List<Produit> produitsExipires = produitService.findByDateFin(date);
		if( produitsExipires != null )
		{
			for( int i=0 ; i< produitsExipires.size() ; i++ )
			{
				Produit produit = produitsExipires.get(i);
				produit.setEstActive(false);
				produitService.updateProduit(produit);
			}
		}
		
	}
	
}
