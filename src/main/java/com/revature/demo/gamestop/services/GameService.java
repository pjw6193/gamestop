package com.revature.demo.gamestop.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.demo.gamestop.beans.Game;
import com.revature.demo.gamestop.controllers.GameController;
import com.revature.demo.gamestop.data.GameRepository;
import com.revature.demo.gamestop.exceptions.GameUnavailableForPurchaseException;
import com.revature.demo.gamestop.exceptions.IllegalRecordUpdateException;
import com.revature.demo.gamestop.exceptions.PrimaryKeyConstraintViolationException;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	private static final Logger log = Logger.getLogger(GameController.class);

	/**
	 * TODO be implemented as part of the demo. Solution below
	 * 
	 * @param game
	 * @return
	 */
	public synchronized Integer purchase(Game game) {
		Game toBuy = gameRepository.findById(game.getId()).get();
		if (toBuy.getQuantity() > 0 && toBuy.getDateAvailable().before(new Date())) {
			toBuy.setQuantity((toBuy.getQuantity() - 1));
			update(toBuy);
			log.info("Purchasing game: " + game.getTitle() + " New quantity: " + toBuy.getQuantity());
			return toBuy.getQuantity();
		} else {
			log.info("Not available: " + game.getTitle());
			throw new GameUnavailableForPurchaseException("Sorry.. game is unavailable for purchase.");
		}
	}

	public List<Game> findAll() {
		return gameRepository.findAll();
	}

	public Game findById(Integer id) {
		return gameRepository.findById(id).orElse(null);
	}

	public List<Game> findByGenre(String genre) {
		return gameRepository.findByGenreContainingIgnoreCase(genre).orElse(new ArrayList<>());
	}

	public Game create(Game game) {
		Optional<Game> toSave = gameRepository.findById(game.getId());
		if (toSave.isPresent()) {
			throw new PrimaryKeyConstraintViolationException("The record with identifier " + game.getId()
					+ " already exists. Please select a unique identifier.");
		} else {
			return gameRepository.save(game);
		}
	}

	public Game update(Game game) {
		Optional<Game> toUpdate = gameRepository.findById(game.getId());
		if (toUpdate.isPresent()) {
			Game update = toUpdate.get();
			update.setDateAvailable(game.getDateAvailable());
			update.setImageUrl(game.getImageUrl());
			update.setPrice(game.getPrice());
			update.setQuantity(game.getQuantity());
			update.setTitle(game.getTitle());
			return gameRepository.save(update);
		} else {
			throw new IllegalRecordUpdateException("The record with identifier " + game.getId()
					+ " was not found. You cannot update a record that does not exist.");
		}
	}

	public void delete(Game game) {
		gameRepository.delete(game);
	}

}
