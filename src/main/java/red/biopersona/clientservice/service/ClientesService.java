package red.biopersona.clientservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import red.biopersona.clientservice.controller.exception.CollectionsServiceException;
import red.biopersona.clientservice.model.ErrorHandlerDTO;
import red.biopersona.clientservice.model.ResponseClientesDisponiblesDTO;


@Slf4j
@Service
public class ClientesService implements IClientesService {
	
	@Autowired
	private IPersistenceService persistence;
	
    List<ResponseClientesDisponiblesDTO> clientesDisponiblesL;          
    	
	/***
	 * Metodo que permite validar si es posible operar sobre el cliente
	 * 
	 * @param Client nombre del cliente
	 * @return true, si cumple con la peticion
	 * @throws CollectionsServiceException 
	 */
	public boolean canOperateTheClient(String client) throws CollectionsServiceException {
		boolean res=false;
		res=clientesDisponiblesL.stream().allMatch(x->x.getLlave().equals(client));
		log.info("Buscando al cliente "+client+" se encontro?"+res);
		return res;
	}
	
	public List<ResponseClientesDisponiblesDTO> getClientesDisponibles() {
		log.info("getClientesDisponibles...");
		Object responseIntrospect=persistence.getClientesDisponibles();
		if (responseIntrospect.getClass() == ErrorHandlerDTO.class) {
			ErrorHandlerDTO error=(ErrorHandlerDTO)responseIntrospect;
			log.error("erro al consumir, el servicio de persistence "+error.toString());
		} else {
			clientesDisponiblesL=Arrays.asList((ResponseClientesDisponiblesDTO[])responseIntrospect);
			log.info("clientes, disponibles "+clientesDisponiblesL.size());
		}
		return clientesDisponiblesL;
	}
	
	

}
