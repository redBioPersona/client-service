package red.biopersona.clientservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import red.biopersona.clientservice.controller.exception.CollectionsServiceException;
import red.biopersona.clientservice.service.IClientesService;


/***
 * 
 * @author Omar Barrera Valentin
 *
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController{
	
	@Autowired
	IClientesService clientesService;
	
	
	@PostMapping(value = "/existsClient", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> existeCliente(@RequestParam String key) throws CollectionsServiceException  {
		HttpStatus code = HttpStatus.OK;
		boolean resul=clientesService.canOperateTheClient(key);
		log.info("validando si existe el cliente "+key+"?="+resul);
		return new ResponseEntity<>(resul, code);
	}
}
