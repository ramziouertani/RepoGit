package fr.bpifrance.fmg.ctg.fct.mock.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bpifrance.fmg.ctg.fct.mock.Model.ErrorDetails;
import fr.bpifrance.fmg.ctg.fct.mock.Model.Individu;
import fr.bpifrance.fmg.ctg.fct.mock.Repository.IndividuRepo;

@Service
public class IndividuService {
	
	@Autowired 
	IndividuRepo individuRepo;
	
	public List<Individu> allIndividus(){
		
		return individuRepo.findAll();
	}
	
	public Individu getByCle(String cle) {
		return individuRepo.findById(cle).get();
	}
	
	public List<Individu> getIndividuByParent(String parent) {
		
		List<Individu> list =individuRepo.findAll().
				stream().
				filter(ob -> ob.getCle().equals(parent)).
				collect(Collectors.toList());
		
		return list;
	}
	
	public List<Individu> getIndividuByNom(String nom) {
		
		List<Individu> list =individuRepo.findAll().
				stream().
				filter(ob -> ob.getType().equals(nom)).
				collect(Collectors.toList());
		
		Individu ind = list.get(0);
		return list;
	}
	
	public void createIndividu(Individu individu) {
		individuRepo.save(individu);		
	}
	
	public Boolean individuExist(String cle) {
		
		return individuRepo.existsById(cle);
	}
	
	public List<Individu> individuExistParam(String nom,String cle) {
		
		List<Individu> list =individuRepo.findAll().
				stream().
				filter(ob -> ob.getType().equals(nom)).
				collect(Collectors.toList());
		
		return list.stream().filter(obj -> obj.getCle().equals(cle)).collect(Collectors.toList());
		
	}

	public List<ErrorDetails> checkParameter(Individu individu) {
		
		
		List<ErrorDetails> listDetailError = new ArrayList<>();
	
		 if (!StringUtils.isNumeric(individu.getCle())){
			 ErrorDetails errorDetail = new ErrorDetails("IMX-0001","Type de Parameter","CLE");			
			  listDetailError.add(errorDetail);			  
		 }				 
		 if (StringUtils.isNumeric(individu.getNom())){	
			 ErrorDetails errorDetail = new ErrorDetails("IMX-0001","Type de Parameter","NOM");
				  listDetailError.add(errorDetail);				  
		 }
		 if (!StringUtils.isNumeric(individu.getParent())){
			 ErrorDetails errorDetail = new ErrorDetails("IMX-0001","Type de Parameter","PARENT");
				  listDetailError.add(errorDetail);				  
		 }
		 if (StringUtils.isNumeric(individu.getStatus())){
			 ErrorDetails errorDetail = new ErrorDetails("IMX-0001","Type de Parameter","STATUS");
					  listDetailError.add(errorDetail);					  
		 }
		 if ( StringUtils.isNumeric(individu.getType())){
			 ErrorDetails errorDetail = new ErrorDetails("IMX-0001","Type de Parameter","TYPE");
					  listDetailError.add(errorDetail);					  
		 }									
			
	return listDetailError;
	}
}
