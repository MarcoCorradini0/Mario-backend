package org.tuo.dto;

import org.tuo.game.GameSession;
import java.util.List;

public class PlayerLeaderboardResponse {
    public int rank;
    public List<GameSession> sessions;

    public PlayerLeaderboardResponse(int rank, List<GameSession> sessions) {
        this.rank = rank;
        this.sessions = sessions;
    }
}
