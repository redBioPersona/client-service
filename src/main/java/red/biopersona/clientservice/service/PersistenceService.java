package red.biopersona.clientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import lombok.extern.slf4j.Slf4j;
import red.biopersona.clientservice.model.ErrorHandlerDTO;
import red.biopersona.clientservice.model.ResponseClientesDisponiblesDTO;

@Slf4j
@Service
public class PersistenceService implements IPersistenceService{
	/***
	 * Url del endPoint instrospect para aplicaciones internas
	 */
	@Value("${urlClient}")
	private String urlClient;

	
	/***
	 * RestOperations de tipo template
	 */
	@Autowired
	private RestOperations oauthServerTemplate;
	
	/***
	 * Metodo que permite el consumo del servicio Introspect
	 * @param Jwt jwt a inyectar en la peticion, el cual fue firmado por la llave privada
	 * @param AccessToken token de acceso, proporcionado por el cliente consumidor
	 * @param Scope scope del cliente
	 * @param tipoAccessToken prefijo del accessToken, permite validar si es persona fisica o app interna
	 */
	@Override
	public Object getClientesDisponibles() {
		try {
			log.info("Invocando al instrospect " + urlClient + " client " + urlClient);
			ResponseEntity<ResponseClientesDisponiblesDTO[]> response = oauthServerTemplate.getForEntity(
					urlClient,
					ResponseClientesDisponiblesDTO[].class);
			log.info("getClientesDisponibles "+response.getStatusCode().value()+" response "+response.getBody().toString());
			return response.getBody();
		} catch (HttpClientErrorException | HttpServerErrorException ex) {
			log.error("Error contacting with oauth server", ex);
			ErrorHandlerDTO abc = new ErrorHandlerDTO();
			abc.setCause("instrospect_" + ex.getRawStatusCode() + "_communication_error");
			abc.setMessage(ex.getMessage() + " - " + ex.getStatusText() + " - " + ex.getResponseBodyAsString());
			return abc;
		} catch (RestClientException e) {
			log.error("Error contacting with oauth server", e);
			ErrorHandlerDTO abc = new ErrorHandlerDTO();
			abc.setCause("instrospect_communication_error");
			abc.setMessage(e.getMessage());
			return abc;
		}
	}
	
}
