package org.tuo.game;

import org.tuo.dto.GameSessionDTO;

public class GameSessionMapper {
    public static GameSessionDTO toDTO(GameSession session){
        GameSessionDTO dto=new GameSessionDTO();
        dto.id=session.id;
        dto.playerOrUsername = session.playerUsername != null ? session.playerUsername : (session.player != null ? session.player.username : "unknown");
        dto.score=session.score;
        dto.levelReached=session.levelReached;
        dto.durationSeconds=session.durationSeconds;
        dto.validated=session.validated;
        dto.startedAt=session.startedAt;
        dto.endedAt=session.endedAt;
        dto.status=session.status;
        return dto;
    }
}
