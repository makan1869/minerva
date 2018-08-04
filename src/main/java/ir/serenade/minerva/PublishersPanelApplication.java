package ir.serenade.minerva;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PublishersPanelApplication {

	private static final Logger log = LoggerFactory.getLogger(PublishersPanelApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PublishersPanelApplication.class, args);
	}
}
