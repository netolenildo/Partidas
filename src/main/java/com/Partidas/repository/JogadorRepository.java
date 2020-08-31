package com.Partidas.repository;

import org.springframework.data.repository.CrudRepository;

import com.Partidas.models.Jogador;
import com.Partidas.models.Partida;

public interface JogadorRepository extends CrudRepository<Jogador, Long>{
	
	Jogador findJogadorById(Long id);
}
