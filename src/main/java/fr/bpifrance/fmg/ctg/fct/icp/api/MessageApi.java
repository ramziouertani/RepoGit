package fr.bpifrance.fmg.ctg.fct.icp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bpifrance.fmg.ctg.fct.icp.model.Message;

@RestController
public class MessageApi {

	@PostMapping("/messages")
	public ResponseEntity recieveMessage(Message message)
	{
		return new ResponseEntity(HttpStatus.OK);
	}
}
