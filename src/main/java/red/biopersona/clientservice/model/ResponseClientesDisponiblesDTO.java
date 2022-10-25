package red.biopersona.clientservice.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseClientesDisponiblesDTO implements Serializable {

    /** 
     * Variable para serializar la clase.
     */
    private static final long serialVersionUID = 1L;

	
	private String key;
	private String companyName;
	private String direccion;
	private Object logo;
	private String contactoNombre;
	private String contactoEmail;
	private String contactoNumero;

	private boolean puedeConsumirIris=false;
	private boolean puedeConsumirFace=false;
	private boolean puedeConsumirFinger=false;
	private boolean active=false;
	
	private int minOperacionesDiarias;
	private Date maxDateUso;
	
	//Información sobre creación/actualización
	private String createdBy;
	private String updatedBy;
    private Date createdAt;
    private Date updatedAt;
}
