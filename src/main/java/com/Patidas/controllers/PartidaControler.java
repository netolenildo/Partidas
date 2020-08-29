package com.Patidas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Patidas.models.Jogador;
import com.Patidas.models.Partida;
import com.Patidas.repository.JogadorRepository;
import com.Patidas.repository.PartidaRepository;

@Controller
@RequestMapping("/partida")
public class PartidaControler {
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Autowired
	private JogadorRepository jogadorRepository;

	@RequestMapping("/")
	public ModelAndView index() {
		Iterable<Partida> partidas = partidaRepository.findAll();
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("partidas", partidas);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrarPartida", method = RequestMethod.GET)
	public String cadastrarPartida() {
		return "partida/form";
	}
	
	@RequestMapping(value = "/cadastrarPartida", method = RequestMethod.POST)
	public String cadastrarPartida(@Valid Partida partida, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Campos Invalidos!");
			return "redirect:/partida/cadastrarPartida";
		}
		
		partidaRepository.save(partida);
		
		attributes.addFlashAttribute("mensagem","Partida salva com sucesso.");
		
		return "redirect:/partida/";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalharPartida(@PathVariable Long id) {
		Partida partida = partidaRepository.findPartidaById(id);
		
		Iterable<Jogador> jogadores = partida.getJogadores();
		
		ModelAndView modelAndView = new ModelAndView("visualizar");
		modelAndView.addObject("partida", partida);
		modelAndView.addObject("jogadores", jogadores);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String detalharPartida(@PathVariable Long id, @Valid Jogador jogador, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem","Campos Invalidos!");
			return "redirect:/partida/" + id;
		}
		
		jogadorRepository.save(jogador);
		
		attributes.addFlashAttribute("mensagem","Jogador adicionado com sucesso.");
		
		return "redirect:/partida/" + id;
	}
	
	@RequestMapping("/removerPartida/{id}")
	public String deletarPartida(@PathVariable Long id) {
		Partida partida = partidaRepository.findPartidaById(id);
		
		partidaRepository.delete(partida);
		
		return "redirect:/partida/";
	}
	
	@RequestMapping("/removerJogador/{idPartida}/{idJogador}")
	public String removerJogador(@PathVariable Long idPartida, Long idJogador) {
		Jogador jogador = jogadorRepository.findJogadorById(idJogador);
		
		Partida partida = partidaRepository.findPartidaById(idPartida);
		
		partida.getJogadores().remove(jogador);
		
		partidaRepository.save(partida);
		
		return "redirect:/partida/" + partida.getId();
	}
	
	
}
