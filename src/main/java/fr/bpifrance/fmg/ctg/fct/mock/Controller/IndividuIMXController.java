package fr.bpifrance.fmg.ctg.fct.mock.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bpifrance.fmg.ctg.fct.mock.Exception.IndividuException;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorDetails;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorResponse;
import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorRetour;
import fr.bpifrance.fmg.ctg.fct.mock.Model.Individu;
import fr.bpifrance.fmg.ctg.fct.mock.Service.IndividuService;

@RestController
@RequestMapping("/imx/rest/v1/")
public class IndividuIMXController {
	
	@Autowired
	IndividuService individuRepository;
	
	@GetMapping("individus")
	public ResponseEntity<Object> getAll_Individu(){
		if(individuRepository.allIndividus().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).
					body("HttpStatus - " + HttpStatus.NO_CONTENT + System.getProperty("line.separator") + " Empty Response"   );
		}
		
			return new ResponseEntity<>(individuRepository.allIndividus(),HttpStatus.OK);
	}	
		
	@GetMapping("individu/{cle}")
	public ResponseEntity<Object> getIndividu(@PathVariable(value = "cle") String cle){

		if(!individuRepository.individuExist(cle))	{
			
			List<ErrorDetails> listDetailError = new ArrayList<>();
			ErrorDetails errorDetail = new ErrorDetails("IMX-0007","could not fount the parameter : " + cle ,"Parameter introuvable");		  
			listDetailError.add(errorDetail);				  
			ErrorRetour error = new ErrorRetour("IMX-ERRORS","fonctionelle",listDetailError);
		return new ResponseEntity<Object>(error, HttpStatus.OK);
		}		
		return new ResponseEntity<>(individuRepository.getByCle(cle),HttpStatus.OK);	
	}
	
	@GetMapping("individus/{nom}")
	public ResponseEntity<Object> getIndividuByNom(@PathVariable(value = "nom") String nom){

		if(nom.equals("SIG")) {	
				return new ResponseEntity<>(individuRepository.getIndividuByNom("ENTREPRISE"),HttpStatus.OK);						
		}else if(nom.equals("ETA")) {					
			return new ResponseEntity<>(individuRepository.getIndividuByNom("ETABLISSEMENT"),HttpStatus.OK);
		}else if(nom.equals("INT")) {								
				return new ResponseEntity<>(individuRepository.getIndividuByNom("INTERLOCUTEUR"),HttpStatus.OK);			
		}else if(nom.equals("ADD")) {								
				return new ResponseEntity<>(individuRepository.getIndividuByNom("ADRESSE"),HttpStatus.OK);			
		}else {
				List<ErrorDetails> listDetailError = new ArrayList<>();
				ErrorDetails errorDetail = new ErrorDetails("IMX-0007","could not fount the parameter : " + nom ,"Parameter introuvable");		  
				  listDetailError.add(errorDetail);				  
					ErrorRetour error = new ErrorRetour("IMX-ERRORS","fonctionelle",listDetailError);
					  error.setListErrors(listDetailError);
				 return new ResponseEntity<Object>(error, HttpStatus.OK);		
		}						
	}
	
	@GetMapping("individus/{nom}/{cle}")
	public ResponseEntity<Object> getIndividuParam(@PathVariable(value = "nom") String nom,@PathVariable(value = "cle") String cle){

		if(!individuRepository.individuExist(cle))	{
			List<ErrorDetails> listDetailError = new ArrayList<>();
			ErrorDetails errorDetail = new ErrorDetails("IMX-0007","could not fount the parameter : " + cle ,"Parameter introuvable");		  
			  listDetailError.add(errorDetail);				  
				ErrorRetour error = new ErrorRetour("IMX-ERRORS","fonctionelle",listDetailError);
				  error.setListErrors(listDetailError);

			 return new ResponseEntity<Object>(error, HttpStatus.OK);
		}else {
		
		   if(nom.equals("SIG")) {
		     return new ResponseEntity<>(individuRepository.individuExistParam("ENTREPRISE", cle),HttpStatus.OK);						
		   }else if(nom.equals("ETA")) {
		     return new ResponseEntity<>(individuRepository.individuExistParam("ETABLISSEMENT", cle),HttpStatus.OK);
		   }else if(nom.equals("INT")) {
		     return new ResponseEntity<>(individuRepository.individuExistParam("INTERLOCUTEUR", cle),HttpStatus.OK);			
		   }else if(nom.equals("ADD")) {
		     return new ResponseEntity<>(individuRepository.individuExistParam("ADRESSE", cle),HttpStatus.OK);			
		   }else {
				List<ErrorDetails> listDetailError = new ArrayList<>();
				ErrorDetails errorDetail = new ErrorDetails("IMX-0007","Le parameter: " + nom  + " n'existe pas  ","Parameter introuvable");		  
				  listDetailError.add(errorDetail);				  
					ErrorRetour error = new ErrorRetour("IMX-ERRORS","Pas de Reponse",listDetailError);
					  error.setListErrors(listDetailError);
				 return new ResponseEntity<Object>(error, HttpStatus.OK);		
		    }
		}
		   
	}	
	
	@PostMapping("individus" )	
    public ResponseEntity<Object> create(@RequestBody Individu individu) throws IndividuException{
		
		List<ErrorDetails> list = individuRepository.checkParameter(individu);
		if(!list.isEmpty()) {			
			ErrorRetour error = new ErrorRetour("IMX-ERRORS","ERREUR PARAMETER",list);
			return new ResponseEntity<Object>(error, HttpStatus.OK);			
		}
		
		List<ErrorDetails> listDetailError = new ArrayList<>();
		if(!individuRepository.individuExist(individu.getCle())){	
			if(individuRepository.getIndividuByParent(individu.getParent()).isEmpty() && !(individu.getType().equals("ENTREPRISE")) ){

				ErrorDetails errorDetail = new ErrorDetails("IMX-0002","Parent: " + individu.getParent()  + " not Fount to add   " + individu.getType(),"dependence");		  			  				
				  listDetailError.add(errorDetail);				  
					ErrorRetour error = new ErrorRetour("IMX-ERRORS","Fonctionelle",listDetailError);			  
				 return new ResponseEntity<Object>(error, HttpStatus.OK);
			}
			else {
				individuRepository.createIndividu(individu);				
				return new ResponseEntity<Object>(individu.getType() + "  Ok.", HttpStatus.OK);
			}						   
		}
		else{	
			ErrorDetails errorDetail = new ErrorDetails("IMX-0004",individu.getType() +" Exist in database","Data Duplicate");		  			  				
			  listDetailError.add(errorDetail);				  		
			 ErrorRetour error = new ErrorRetour("IMX-ERRORS","Fonctionelle ",listDetailError);
			return new ResponseEntity<Object>(error, HttpStatus.OK);
	    }	
	}
	
	@PutMapping(value = "individus/{cle}")
	public ResponseEntity<Object> updateIndividu(@RequestBody Individu individuDetail) {	
		
		List<ErrorDetails> list = individuRepository.checkParameter(individuDetail);
		if(!list.isEmpty()) {			
			ErrorRetour error = new ErrorRetour("IMX-ERRORS","ERREUR PARAMETER",list);
			return new ResponseEntity<Object>(error, HttpStatus.OK);			
		}
		
		if(!individuRepository.individuExist(individuDetail.getCle()))	{
			
			List<ErrorDetails> listDetailError = new ArrayList<>();
			ErrorDetails errorDetail = new ErrorDetails("IMX-0007","could not fount the parameter : " + individuDetail.getCle() ,"Parameter introuvable");		  
			listDetailError.add(errorDetail);				  
			ErrorRetour error = new ErrorRetour("IMX-ERRORS","fonctionelle",listDetailError);
			  
			 return new ResponseEntity<Object>(error, HttpStatus.OK);
			 }
				
		Individu individuChercher = individuRepository.getByCle(individuDetail.getCle());
		
		individuChercher.setNom(individuDetail.getNom());
		individuChercher.setParent(individuDetail.getParent());
		individuChercher.setStatus("valid");
		individuChercher.setType(individuDetail.getType());
			 
		individuRepository.createIndividu(individuDetail);		   
		return new ResponseEntity<Object>(individuDetail.getType() + "  modified.", HttpStatus.OK);
	}


}
