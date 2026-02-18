package org.tuo.dto;

import java.time.Instant;

public class GameSessionDTO {
    public Long id;
    public String playerOrUsername;
    public String status;
    public boolean validated;
    public Integer score;
    public Integer levelReached;
    public Integer durationSeconds;
    public Instant startedAt;
    public Instant endedAt;
}
