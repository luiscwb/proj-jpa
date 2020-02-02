package br.com.lam.prod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProdutoApplication {

	private static final Logger log = LoggerFactory.getLogger(ProdutoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProdutoApplication.class, args);
	}


}
