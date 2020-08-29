package com.Patidas.repository;

import org.springframework.data.repository.CrudRepository;

import com.Patidas.models.Partida;

public interface PartidaRepository extends CrudRepository<Partida, Long>{
	Partida findPartidaById(Long id);
}
