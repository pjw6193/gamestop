package com.revature.demo.gamestop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.demo.gamestop.beans.Game;
import com.revature.demo.gamestop.services.GameService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@PostMapping(value="/games/purchase")
	public ResponseEntity<Integer> purchase(@Valid @RequestBody Game game) {
		return new ResponseEntity<>(gameService.purchase(game), HttpStatus.OK);
	}
	
	@GetMapping(value="/games/all")
	public ResponseEntity<List<Game>> findAll() {
		return new ResponseEntity<>(gameService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value="/games/{id}")
	public ResponseEntity<Game> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(gameService.findById(id), HttpStatus.OK);
	}

	@GetMapping(value="/games/genre/{genre}")
	public ResponseEntity<List<Game>> findByGenre(@PathVariable String genre) {
		return new ResponseEntity<>(gameService.findByGenre(genre), HttpStatus.OK);
	}

	@PostMapping(value="/games")
	public ResponseEntity<Game> create(@Valid @RequestBody Game game) {
		return new ResponseEntity<>(gameService.create(game), HttpStatus.CREATED);
	}

	@PutMapping(value="/games")
	public ResponseEntity<Game> update(@Valid @RequestBody Game game) {
		return new ResponseEntity<>(gameService.update(game), HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value="/games")
	public ResponseEntity<Void> delete(@Valid @RequestBody Game game) {
		gameService.delete(game);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
