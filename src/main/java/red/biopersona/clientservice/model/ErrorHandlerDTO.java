package red.biopersona.clientservice.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 * Datos que mapean el errorHandler
 * @author Omar Barrera Valentin
 */
@Getter
@Setter
@ToString
public class ErrorHandlerDTO implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/***
	 * Mensaje del error
	 */
	private String message;
	/***
	 * Causa del error
	 */
	private String cause;
}
