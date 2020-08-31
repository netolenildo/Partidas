package com.Partidas.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty
	private String titulo;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotNull
	private Integer mapa;
	
	@ManyToMany
	@Size(min = 0, max = 10, message = "A partida não pode possuir mais do que 10 jogadores.")
	private List<Jogador> jogadores;
	
	public String nomeMapa() {
		return Mapa.getNome(mapa);
	}
	
	public Partida() {
		jogadores = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getMapa() {
		return mapa;
	}

	public void setMapa(Integer mapa) {
		this.mapa = mapa;
	}

	public List<Jogador> getJogadores() {
		return jogadores;
	}

	public void setJogadores(List<Jogador> jogadores) {
		this.jogadores = jogadores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
