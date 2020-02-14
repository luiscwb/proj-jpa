package com.loclab.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.loclab.persist.LocacaoRepository;

@EnableScheduling
@Component
public class ScheduleJob {

	@Autowired
	private LocacaoRepository locacaoRepository;
	

    @Scheduled(cron = "${cron.expression}")
	//@Scheduled(fixedDelay = 10000, initialDelay = 500)
	private void cleanLocacoes() {
		System.out.println("Rodando schedule ....");
		locacaoRepository.findAll().forEach( loc -> System.out.println(loc.getDataInicial()));
	}
}
