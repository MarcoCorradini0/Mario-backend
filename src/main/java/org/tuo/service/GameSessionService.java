package org.tuo.service;

import java.time.Instant;

import org.tuo.game.GameSession;
import org.tuo.game.GameSessionRepository;
import org.tuo.player.Player;
import org.tuo.player.PlayerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class GameSessionService {
    @Inject
    GameSessionRepository sessionRepo;
    @Inject
    PlayerRepository playerRepo;
    //Start
    @Transactional
    public GameSession startNewSession(String username){
        Player player=playerRepo.findByUsername(username)
                                .orElseThrow(()->new RuntimeException("Player not found"));
        GameSession session=new GameSession();
        session.player=player;
        session.playerUsername = player.username;
        session.score = 0;
        session.levelReached = 1;
        session.durationSeconds = 0;
        session.validated = false;
        session.startedAt = Instant.now();
        session.status = "IN_PROGRESS";

        sessionRepo.persist(session);
        return session;
    }
    //End
    public GameSession endSession(Long sessionId, int score, int level, int duration){
        GameSession session=sessionRepo.findById(sessionId);
        if (session==null) {
            throw new IllegalArgumentException("Session not found");
        }
        if (!"IN_PROGESS".equals(session.status)) {
            throw new IllegalArgumentException("Session already finished");
        }
        session.score=score;
        session.levelReached=level;
        session.durationSeconds=duration;
        session.endedAt=Instant.now();
        session.validated=true;
        session.status="FINISHED";

        //Update player stats
        Player player=session.player;
        player.totalGames+=1;
        player.totalPoints+=score;
        if (score>player.bestScore) {
            player.bestScore=score;
        }
        return session;
    }
}
