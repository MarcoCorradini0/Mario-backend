package org.tuo.player;

public class PlayerResponse {
    public Long id;
    public String username;
    public String email;
    public Integer bestScore;
    public Integer totalGames;
    public Integer totalPoints;
    public boolean isActive;
    public boolean isBanned;
    public String role;

    public static PlayerResponse fromPlayer(Player p) {
        PlayerResponse resp = new PlayerResponse();
        resp.id = p.id;
        resp.username = p.username;
        resp.email = p.email;
        resp.bestScore = p.bestScore;
        resp.totalGames = p.totalGames;
        resp.totalPoints = p.totalPoints;
        resp.isActive = p.isActive;
        resp.isBanned = p.isBanned;
        resp.role = p.role.name();
        return resp;
    }
}
