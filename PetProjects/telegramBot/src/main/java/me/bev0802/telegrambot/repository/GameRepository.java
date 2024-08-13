package me.bev0802.telegrambot.repository;

import me.bev0802.telegrambot.model.Game;
import me.bev0802.telegrambot.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaSpecificationExecutor<Game>, JpaRepository<Game, Long> {
}

