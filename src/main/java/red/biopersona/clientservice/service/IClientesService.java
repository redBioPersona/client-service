package red.biopersona.clientservice.service;

import java.util.List;

import red.biopersona.clientservice.controller.exception.CollectionsServiceException;
import red.biopersona.clientservice.model.ResponseClientesDisponiblesDTO;

public interface IClientesService {
	public boolean canOperateTheClient(String client) throws CollectionsServiceException ;
	List<ResponseClientesDisponiblesDTO> getClientesDisponibles();
}
