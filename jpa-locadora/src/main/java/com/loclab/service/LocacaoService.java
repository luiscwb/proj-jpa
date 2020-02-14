package com.loclab.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loclab.entity.Cliente;
import com.loclab.entity.Locacao;
import com.loclab.entity.Local;
import com.loclab.persist.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository repository;
	
	
	@Transactional
	public Locacao realizarReserva(Local local, Cliente cliente, Date dataInicial, Date dataFinal) {
		Locacao retorno = null;
		
		//Valida se nao existe alguma reserva do Local no periodo de datas solicitado
		List<Locacao> locacao = repository.checkAvailability(local.getId(), dataInicial, dataFinal);
		if ( locacao == null || locacao.size() == 0 ) {
			retorno = repository.save(Locacao.createInstance(cliente, local, dataInicial, dataFinal));
		}
		return retorno;
	}
	
	
	@Transactional
	public void cancelarReserva(Locacao locacao) {
		repository.deleteById(locacao.getId());
	}
	
	
	@Transactional
	public Locacao alterarDatasReserva(Locacao locacao, Date dataInicial, Date dataFinal) {
		Locacao retorno = null;
		
		Optional<Locacao> dbLocacao = repository.findById(locacao.getId());
		if (dbLocacao.isPresent()) {
			// Para poder alterar deve ser validado senao existe outra reserva nas novas datas solicitadas para outro cliente
			List<Locacao> availability = repository.checkAvailability(locacao.getLocal().getId(), dataInicial, dataFinal);
			if ( availability == null || availability.size() == 0 || availability.get(0).getCliente().getId() == locacao.getCliente().getId() )
				dbLocacao.get().setDataInicial(dataInicial);
				dbLocacao.get().setDataFinal(dataFinal);
				retorno = dbLocacao.get();
		}
		return retorno;
	}
	
	@Transactional
	public void arquivarLocacoesAntigas() {
		
		Date hoje = new Date();
		Date ontem = new Date(hoje.getTime() - (1000 * 60 * 60 * 24));
		
		repository.deleteLessThan(ontem);
	}
}
