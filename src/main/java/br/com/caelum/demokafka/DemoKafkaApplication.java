package br.com.caelum.demokafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootApplication
public class DemoKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoKafkaApplication.class, args);
	}

}

@RestController
class CompraController {

	private final KafkaTemplate<UUID, PurchaseFinished> kafkaTemplate;

	CompraController(KafkaTemplate<UUID, PurchaseFinished> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping("/purchase")
	public ResponseEntity<PurchaseFinished> generatePurChase() {
		var cuscuz = new PurchaseFinished("Cuscuz", BigDecimal.ONE);
		kafkaTemplate.sendDefault(cuscuz);
		return ResponseEntity.ok(cuscuz);
	}
}

@Component
class MailSender {

	private static final Logger LOG = LoggerFactory.getLogger(MailSender.class);

	@KafkaListener(topics = "purchase-spring")
	public void purchaseProcessor(PurchaseFinished purchaseFinished) {
		LOG.info("Sending email for purchase: {}", purchaseFinished);
	}
}
