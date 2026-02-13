package org.tuo.game;

import java.time.Instant;

import org.tuo.player.Player;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name="player_id", nullable = false)
    public Player player;

    public Integer score;
    public Integer levelReached;
    public Integer durationSeconds;
    public boolean validated;
    public Instant startedAt;
    public Instant endedAt;
}
