package me.bev0802.telegrambot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.bev0802.telegrambot.model.Player;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "player1_id")
    private Player player1;

    @Column
    private int player1Points;

    @ManyToOne
    @JoinColumn (name = "player2_id")
    private Player player2;

    @Column
    private int player2Points;

}

