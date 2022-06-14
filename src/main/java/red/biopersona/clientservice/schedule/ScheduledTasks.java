package red.biopersona.clientservice.schedule;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import red.biopersona.clientservice.service.IClientesService;

@Component
public class ScheduledTasks {
	@Autowired
	IClientesService clientesService;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedDelay = 3600000, initialDelay = 1000)
	public void reportCurrentTime() {
		log.info("The time, consuming clients: {}", dateFormat.format(new Date()));
		clientesService.getClientesDisponibles();
	}
}