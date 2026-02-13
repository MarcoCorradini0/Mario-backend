-- ===============================
-- PLAYERS TABLE
-- ===============================
CREATE TABLE players(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    best_score INTEGER NOT NULL DEFAULT 0,
    total_games INTEGER NOT NULL DEFAULT 0,
    total_points INTEGER NOT NULL DEFAULT 0,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    is_banned BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_players_username ON players(username);
CREATE INDEX idx_players_email on players(email);

-- ===============================
-- GAME SESSIONS TABLE
-- ===============================
CREATE TABLE game_sessions(
    id BIGSERIAL PRIMARY KEY,
    player_id BIGINT NOT NULL,
    score INTEGER NOT NULL,
    level_reached INTEGER NOT NULL,
    duration_seconds INTEGER NOT NULL,
    validated BOOLEAN NOT NULL DEFAULT FALSE,
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_player
        FOREIGN KEY(player_id)
        REFERENCES player(id)
        ON DELETE CASCADE
);
CREATE INDEX idx_game_sessions_player_id ON game_sessions(player_id);
CREATE INDEX idx_game_sessions_score ON game_sessions(score DESC);
CREATE INDEX idx_game_sessions_created_at ON game_sessions(started_at);