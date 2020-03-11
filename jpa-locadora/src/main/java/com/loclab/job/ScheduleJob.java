package com.loclab.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.loclab.persist.LocacaoRepository;

@EnableScheduling
@Component
public class ScheduleJob {

	private static final Logger log = LoggerFactory.getLogger(ScheduleJob.class);
	
	@Autowired
	private LocacaoRepository locacaoRepository;

	// second, minute, hour, day of month, month, day(s) of week
	// https://riptutorial.com/spring/example/21209/cron-expression

	@Scheduled(cron = "${cron.expression}")
	// @Scheduled(fixedDelay = 10000, initialDelay = 500)
	private void cleanLocacoes() {
		log.info("Rodando schedule ....");
		locacaoRepository.findAll().forEach(loc -> log.info(loc.getDataInicial().toString()));
	}
}
