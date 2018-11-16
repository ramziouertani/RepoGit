package com.example.imx.Controller;

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

import com.example.imx.Exception.IndividuException;
import com.example.imx.Model.ErrorDetails;
import com.example.imx.Model.ErrorResponse;
import com.example.imx.Model.ErrorRetour;
import com.example.imx.Model.Individu;
import com.example.imx.Service.IndividuService;

@RestController
@RequestMapping("imx/rest/v1/")
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
		
	@GetMapping("individus/{cle}")
	public ResponseEntity<Object> getIndividu(@PathVariable(value = "cle") String cle){

		if(!individuRepository.individuExist(cle))	{
			
			List<ErrorDetails> listDetailError = new ArrayList<>();
			ErrorDetails errorDetail = new ErrorDetails();		  
			ErrorRetour error = new ErrorRetour();
		  	  error.setStatus("IMX-ERRORS");
		      error.setMessage("Pas de Reponse");
			  errorDetail.setCode("IMX-0007");
			  errorDetail.setDescription("Le parameter: " + cle  + " n'existe pas  ");
			  errorDetail.setTitle("Parameter introuvable");
			  listDetailError.add(errorDetail);				  
			  error.setListErrors(listDetailError);
			  
			 return new ResponseEntity<Object>(error, HttpStatus.OK);
			 }		
		return new ResponseEntity<>(individuRepository.getByCle(cle),HttpStatus.OK);
		
	}
	
	@PostMapping("individus" )
	public ResponseEntity<Object> create(@RequestBody Individu individu) throws IndividuException{
		
		List<ErrorDetails> list = individuRepository.checkParameter(individu);
		if(!list.isEmpty()) {
			ErrorRetour error = new ErrorRetour();

			error.setStatus("IMX-ERRORS");
			error.setMessage("ERREUR PARAMETER");
			error.setListErrors(list);
			return new ResponseEntity<Object>(error, HttpStatus.OK);
			
		}
		
		List<ErrorDetails> listDetailError = new ArrayList<>();
		ErrorDetails errorDetail = new ErrorDetails();		  
		ErrorRetour error = new ErrorRetour();

		if(!individuRepository.individuExist(individu.getCle())){	
			if(individuRepository.getIndividuByParent(individu.getParent()).isEmpty()&& !(individu.getType().equals("ENTREPRISE")) ){
				
			  	  error.setStatus("IMX-ERRORS");
			      error.setMessage("dépendence introuvable");
				  errorDetail.setCode("IMX-0002");
				  errorDetail.setDescription("Le parent: " + individu.getParent()  + " n'exist pas pour ajouter  " + individu.getType());
				  errorDetail.setTitle("dépendence");
				  listDetailError.add(errorDetail);				  
				  error.setListErrors(listDetailError);
				  
				 return new ResponseEntity<Object>(error, HttpStatus.OK);
			}
			else {
				individuRepository.createIndividu(individu);				
				return new ResponseEntity<Object>(individu.getType() + "  Ok.", HttpStatus.OK);
			}						   
		}
		else{			
			
			  error.setStatus("IMX-ERRORS");
			  error.setMessage("Doublement les données ");
			  errorDetail.setCode("IMX-0004");
			  errorDetail.setDescription(individu.getType() + "  exist déja");
			  errorDetail.setTitle("Doublement");
			  listDetailError.add(errorDetail);			  
			  error.setListErrors(listDetailError);
			
			return new ResponseEntity<Object>(error, HttpStatus.OK);
	    }	
	}
	
	@PutMapping(value = "individus/{cle}")
	public ResponseEntity<Object> updateIndividu(@RequestBody Individu individuDetail) {	
		
		if(!individuRepository.individuExist(individuDetail.getCle()))	{
			
			List<ErrorDetails> listDetailError = new ArrayList<>();
			ErrorDetails errorDetail = new ErrorDetails();		  
			ErrorRetour error = new ErrorRetour();
		  	  error.setStatus("IMX-ERRORS");
		      error.setMessage("Pas de Reponse");
			  errorDetail.setCode("IMX-0007");
			  errorDetail.setDescription("Le parameter: " + individuDetail.getCle()  + " n'existe pas  ");
			  errorDetail.setTitle("Parameter introuvable");
			  listDetailError.add(errorDetail);				  
			  error.setListErrors(listDetailError);
			  
			 return new ResponseEntity<Object>(error, HttpStatus.OK);
			 }
				
		Individu individuChercher = individuRepository.getByCle(individuDetail.getCle());
		
		individuChercher.setNom(individuDetail.getNom());
		individuChercher.setParent(individuDetail.getParent());
		individuChercher.setStatus("valid");
		individuChercher.setType(individuDetail.getType());
			 
		   individuRepository.createIndividu(individuDetail);
		   
		   ErrorResponse error = new ErrorResponse();
			error.setCode(HttpStatus.OK.value());
			error.setMessage("Le Status de " + individuDetail.getType() + "  est modifier");
		   return ResponseEntity.status(HttpStatus.CREATED).
				   body("HttpStatus - " + error.getCode() + System.getProperty("line.separator") + "Le Status de " + error.getMessage());
	}


}
