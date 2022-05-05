package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private static final int MAIN_PLAYER_LIFE_POINTS = 100;
    private static final int CIVIL_PLAYER_LIFE_POINTS = 50;

    private Player mainPlayer;
    private Neighbourhood neighbourhood;
    private Map<String, Player> civilPlayers;
    private ArrayDeque<Gun> guns;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.neighbourhood = new GangNeighbourhood();
        this.civilPlayers = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.putIfAbsent(player.getName(), player);

        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type) {
            case "Rifle":
                gun = new Rifle(name);
                break;
            case "Pistol":
                gun = new Pistol(name);
                break;
            default:
                return ConstantMessages.GUN_TYPE_INVALID;
        }
        this.guns.add(gun);

        return String.format(ConstantMessages.GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = this.guns.poll();
        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(gun);
            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), mainPlayer.getName());
        }
        Player player = this.civilPlayers.get(name);
        if (player == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }
        player.getGunRepository().add(gun);

        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
    }

    @Override
    public String fight() {
        this.neighbourhood.action(mainPlayer, civilPlayers.values());
        if (this.mainPlayer.getLifePoints() == MAIN_PLAYER_LIFE_POINTS
                && this.civilPlayers.values().stream().allMatch(player -> player.getLifePoints() == CIVIL_PLAYER_LIFE_POINTS)) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }
        List<Player> deadPlayers = this.civilPlayers.values().stream()
                .filter(player -> !player.isAlive()).collect(Collectors.toList());

        StringBuilder builder = new StringBuilder(ConstantMessages.FIGHT_HAPPENED).append(System.lineSeparator());
        builder.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size() - deadPlayers.size()));
        for (Player player : deadPlayers) {
            this.civilPlayers.remove(player.getName());
        }

        return builder.toString().trim();
    }
}
