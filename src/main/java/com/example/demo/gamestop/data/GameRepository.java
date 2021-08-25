package com.example.demo.gamestop.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.gamestop.beans.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

	public Optional<List<Game>> findByGenreContainingIgnoreCase(String genre);
	
}
