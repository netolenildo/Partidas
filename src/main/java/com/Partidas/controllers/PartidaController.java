package com.Partidas.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Partidas.models.Jogador;
import com.Partidas.models.Partida;
import com.Partidas.repository.JogadorRepository;
import com.Partidas.repository.PartidaRepository;

@Controller
@RequestMapping("/partida")
public class PartidaController {
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;

	@RequestMapping("/index")
	public ModelAndView index() {
		Iterable<Partida> partidas = partidaRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("partidas", partidas);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrarPartida", method = RequestMethod.GET)
	public String cadastrarPartida() {
		return "form";
	}
	
	@RequestMapping(value = "/cadastrarPartida", method = RequestMethod.POST)
	public String cadastrarPartida(@Valid Partida partida, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors() || partida.getMapa() == 0) {
			List<String> erros = new ArrayList<>();
			
			if(partida.getMapa() == 0) {
				erros.add("Mapa inválido!");
			}
			
			for(ObjectError erro : result.getAllErrors()) {
				erros.add(erro.getDefaultMessage());
			}
			
			attributes.addFlashAttribute("erros", erros);
			
			return "redirect:/partida/cadastrarPartida";
		}
		
		partidaRepository.save(partida);
		
		attributes.addFlashAttribute("sucesso","Partida cadastrada com sucesso.");
		
		return "redirect:/partida/cadastrarPartida";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalharPartida(@PathVariable Long id) {
		Partida partida = partidaRepository.findPartidaById(id);
		
		Iterable<Jogador> jogadores = partida.getJogadores();
		
		ModelAndView modelAndView = new ModelAndView("detalhes");
		modelAndView.addObject("partida", partida);
		modelAndView.addObject("jogadores", jogadores);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalharPartida(@PathVariable Long id, @Valid Jogador jogador, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors() || jogador.getPosicao() == 0) {
			List<String> erros = new ArrayList<>();
			
			if(jogador.getPosicao() == 0) {
				erros.add("Posição inválida!");
			}
			
			for(ObjectError erro : result.getAllErrors()) {
				erros.add(erro.getDefaultMessage());
			}
			
			attributes.addFlashAttribute("erros", erros);
			return "redirect:/partida/" + id;
		}
		
		Jogador jogadorDB = jogadorRepository.save(jogador);
		
		Partida partida = partidaRepository.findPartidaById(id);
		partida.getJogadores().add(jogadorDB);
		
		partidaRepository.save(partida);
		
		
		attributes.addFlashAttribute("sucesso","Jogador adicionado com sucesso.");
		
		return "redirect:/partida/" + id;
	}
	
	@RequestMapping("/removerPartida/{id}")
	public String deletarPartida(@PathVariable Long id, RedirectAttributes attributes) {
		Partida partida = partidaRepository.findPartidaById(id);
		
		partidaRepository.delete(partida);
		
		attributes.addFlashAttribute("sucesso","Partida removida com sucesso.");
		
		return "redirect:/partida/index";
	}
	
	@RequestMapping("/removerJogador/{idPartida}/{idJogador}")
	public String removerJogador(@PathVariable Long idPartida, @PathVariable Long idJogador, RedirectAttributes attributes) {
		Jogador jogador = jogadorRepository.findJogadorById(idJogador);
		
		Partida partida = partidaRepository.findPartidaById(idPartida);
		
		partida.getJogadores().remove(jogador);
		
		partidaRepository.save(partida);
		
		attributes.addFlashAttribute("sucesso","Jogador removido com sucesso.");
		
		return "redirect:/partida/" + partida.getId();
	}
	
	
}
