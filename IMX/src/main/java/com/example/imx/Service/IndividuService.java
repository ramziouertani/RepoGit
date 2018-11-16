package com.example.imx.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.imx.Model.ErrorDetails;
import com.example.imx.Model.Individu;
import com.example.imx.Repository.IndividuRepo;

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
		
		List<Individu> list =individuRepo.findAll().stream().filter(ob -> ob.getCle().equals(parent)).collect(Collectors.toList());
		
		return list;
	}
	
	public void createIndividu(Individu individu) {
		individuRepo.save(individu);		
	}
	
	public Boolean individuExist(String cle) {
		
		return individuRepo.existsById(cle);
	}

	public List<ErrorDetails> checkParameter(Individu individu) {
		
		ErrorDetails errorDetail = new ErrorDetails();
		List<ErrorDetails> listDetailError = new ArrayList<>();
	
		 if (!StringUtils.isNumeric(individu.getCle())){
			  errorDetail.setCode("IMX-0001");
			  errorDetail.setTitle("Type de Parameter");
			  errorDetail.setDescription("CLE");
			  listDetailError.add(errorDetail);			  
		 }				 
		 if (StringUtils.isNumeric(individu.getNom())){				 				 
				  errorDetail.setCode("IMX-0001");
				  errorDetail.setTitle("Type de Parameter");
				  errorDetail.setDescription("NOM");
				  listDetailError.add(errorDetail);				  
		 }
		 if (!StringUtils.isNumeric(individu.getParent())){
				  errorDetail.setCode("IMX-0001");
				  errorDetail.setTitle("Type de Parameter");
				  errorDetail.setDescription("PARENT");
				  listDetailError.add(errorDetail);				  
		 }
		 if (StringUtils.isNumeric(individu.getStatus())){
					  errorDetail.setCode("IMX-0001");
					  errorDetail.setTitle("Type de Parameter");
					  errorDetail.setDescription("STATUS");
					  listDetailError.add(errorDetail);					  
		 }
		 if ( StringUtils.isNumeric(individu.getType())){
					  errorDetail.setCode("IMX-0001");
					  errorDetail.setTitle("Type de Parameter");
					  errorDetail.setDescription("TYPE");
					  listDetailError.add(errorDetail);					  
		 }									
			
	return listDetailError;
	}
}
