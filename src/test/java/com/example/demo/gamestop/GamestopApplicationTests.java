package com.example.demo.gamestop;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.gamestop.beans.Game;
import com.example.demo.gamestop.services.GameService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GamestopApplicationTests {

	@Autowired
	private GameService gameService;
	
	/**
	 * TODO be implemented as part of the demo. Solution below
	 */
	@Test
	@Transactional
	public void purchase() {
		Game game = gameService.findById(61);
		int expected = game.getQuantity() - 1;
		int remaining = gameService.purchase(game);
		assertEquals(expected, remaining);
	}

}

