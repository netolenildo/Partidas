package com.Patidas.repository;

import org.springframework.data.repository.CrudRepository;

import com.Patidas.models.Jogador;
import com.Patidas.models.Partida;

public interface JogadorRepository extends CrudRepository<Jogador, Long>{

	Iterable<Jogador> findJogadoresByPartida(Partida partida);
	
	Jogador findJogadorById(Long id);
}
