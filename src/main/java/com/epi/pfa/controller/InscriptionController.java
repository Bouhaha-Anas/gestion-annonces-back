package com.epi.pfa.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.epi.pfa.model.Client;
import com.epi.pfa.model.Compte;
import com.epi.pfa.model.Entrepreneur;
import com.epi.pfa.model.VerificationToken;
import com.epi.pfa.repository.VerificationTokenRepository;
import com.epi.pfa.service.ClientService;
import com.epi.pfa.service.CompteService;
import com.epi.pfa.service.EntrepreneurService;
import com.epi.pfa.utilities.OnRegistrationCompleteEvent;
import com.epi.pfa.utilities.UploadingTask;

@RestController
public class InscriptionController 
{
	public static final String CHEMIN_FICHIERS_CLIENTS = "D:/Ingenieurie/Semestre2/PFA/Work-Space/gestion-annonces/src/main/resources/static/images/uploaded-images/images-clients/";
	public static final String CHEMIN_FICHIERS_ENTREPRENEURS = "D:/Ingenieurie/Semestre2/PFA/Work-Space/gestion-annonces/src/main/resources/static/images/uploaded-images/images-entrepreneurs/";
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	EntrepreneurService entrepreneurService;
	
	@Autowired
	ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	CompteService compteService;
	
	@RequestMapping( value="/inscription", method= RequestMethod.GET )
	public ModelAndView inscription()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("inscription");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionSuccess", method= RequestMethod.GET )
	public ModelAndView inscriptionSuccess()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("confirmationInscription");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/inscriptionConfirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(WebRequest request, @RequestParam("token") String token) 
	{
	    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
	     
	    Compte compte = verificationToken.getCompte();
	    compte.setEnabled(true);
	    
	    compteService.updateCompte(compte);

	  
	    return new ModelAndView("login");
	}
	
	@RequestMapping( value="/inscriptionClient", method= RequestMethod.GET )
	public ModelAndView inscriptionClient()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Client client = new Client();
		
		modelAndView.addObject("client", client);
		modelAndView.setViewName("inscription");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionClient", method= RequestMethod.POST )
	public ModelAndView addClient( Client client, WebRequest request, HttpServletRequest req) throws ServletException,IOException
	{
		ModelAndView modelAndView = new ModelAndView();

		Client tempClient = clientService.findOneByAdresseMail(client.getAdresseMail());
		
		if(tempClient != null)
		{
			modelAndView.setViewName("inscription");
			modelAndView.addObject("client", new Client());
			modelAndView.addObject("errorMessage", "L'adresse Mail est déjà utilisée, veuillez réessayer");
			return modelAndView;
		}
		else
		{
			
			Part part = req.getPart("imageC");
			String nomFichier = UploadingTask.getNomFichier(part);
			if (nomFichier != null && !nomFichier.isEmpty()) 
			{	   
	            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
	            System.out.println(nomFichier);
	            UploadingTask.ecrireFichier(part, nomFichier, CHEMIN_FICHIERS_CLIENTS);
	        }
			client.setImage(CHEMIN_FICHIERS_CLIENTS+nomFichier);
			clientService.addClient(client);
			
			try
			{
				String appUrl = "inscriptionClient";
				applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(client.getCompte(), request.getLocale(), appUrl) );
			}
			catch(Exception e)
			{
				System.out.println("Erreur");
				e.printStackTrace();
			}
			
			modelAndView.setViewName("confirmationInscription");
			
			return modelAndView;
		}
		
	}
	
	@RequestMapping( value="/inscriptionEntrepreneur", method= RequestMethod.GET )
	public ModelAndView inscriptionEntrepreneur()
	{
		ModelAndView modelAndView = new ModelAndView();
		
		Entrepreneur entrepreneur = new Entrepreneur();
		
		modelAndView.addObject("entrepreneur", entrepreneur);
		modelAndView.setViewName("inscription");
		
		return modelAndView;
	}
	
	@RequestMapping( value="/inscriptionEntrepreneur", method= RequestMethod.POST ) 
	public ModelAndView addEntrepreneur( Entrepreneur entrepreneur, WebRequest request, HttpServletRequest req) throws ServletException,IOException
	{
		ModelAndView modelAndView = new ModelAndView();

		Entrepreneur tempEntrepreneur = entrepreneurService.findOneByAdresseMail(entrepreneur.getAdresseMail());
		
		
		if(tempEntrepreneur != null)
		{
			modelAndView.setViewName("inscription");
			modelAndView.addObject("client", new Client());
			modelAndView.addObject("errorMessage", "L'adresse Mail est déjà utilisée, veuillez réessayer");
			return modelAndView;
		}
		else
		{
			Part part = req.getPart("logoE");
			String nomFichier = UploadingTask.getNomFichier(part);
			if (nomFichier != null && !nomFichier.isEmpty()) 
			{	   
	            nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
	            System.out.println(nomFichier);
	            UploadingTask.ecrireFichier(part, nomFichier, CHEMIN_FICHIERS_ENTREPRENEURS);
	        }
			entrepreneur.setLogo(CHEMIN_FICHIERS_ENTREPRENEURS+nomFichier);
			entrepreneurService.addEntrepreneur(entrepreneur);
			
			try
			{
				String appUrl = "inscriptionEntrepreneur";
				System.out.println(appUrl);
				applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(entrepreneur.getCompte(), request.getLocale(), appUrl) );
			}
			catch(Exception e)
			{
				System.out.println("Erreur");
				e.printStackTrace();
			}
			
			modelAndView.setViewName("confirmationInscription");
			
			return modelAndView;
		}
	}
}
