package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Gun;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller{
    private static final int EQUAL_PLAYERS = 0;

    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        Gun gun;
        switch (type){
            case "Pistol":
                gun = new Pistol(name, bulletsCount);
                break;
            case "Rifle":
                gun = new Rifle(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        this.guns.add(gun);
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, gun.getName());
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        Gun gun = this.guns.findByName(gunName);
        if(gun == null){
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }

        Player player;
        switch (type){
            case "CounterTerrorist":
                player = new CounterTerrorist(username, health, armor, gun);
                break;
            case "Terrorist":
                player = new Terrorist(username, health, armor, gun);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        this.players.add(player);

        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, player.getUsername());
    }

    @Override
    public String startGame() {
        List<Player> alivePlayers = this.players.getModels().stream()
                .filter(Player::isAlive).collect(Collectors.toList());

        return this.field.start(alivePlayers);
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();

        this.players.getModels().stream().sorted((firstPlayer, secondPlayer) -> {

            int result = firstPlayer
                    .getClass()
                    .getSimpleName()
                    .compareTo(secondPlayer.getClass()
                            .getSimpleName());

            if (result == EQUAL_PLAYERS) {
                result = Integer.compare(secondPlayer.getHealth(), firstPlayer.getHealth());
            }

            if (result == EQUAL_PLAYERS) {
                result = firstPlayer.getUsername().compareTo(secondPlayer.getUsername());
            }

            return result;
        })
                .forEach(player -> stringBuilder
                        .append(player)
                        .append(System.lineSeparator()));

        return stringBuilder.toString().trim();
    }
}
