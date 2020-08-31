package com.Partidas.repository;

import org.springframework.data.repository.CrudRepository;

import com.Partidas.models.Partida;

public interface PartidaRepository extends CrudRepository<Partida, Long>{
	Partida findPartidaById(Long id);
}
