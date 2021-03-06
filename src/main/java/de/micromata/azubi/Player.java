package de.micromata.azubi;

import java.io.Serializable;

/**
 * Created by jsiebert on 29.09.14.
 */
public class Player implements Serializable{

    private static final long serialVersionUID = -2306082155444323753L;

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Inventory inventory = new Inventory();


    private String playerName;
    private boolean alive;

    public Player() {

    }

    public Player(String playerName, boolean alive) {
        //TODO spieler lebt immer
        //this.inventory = inventory;
        this.playerName = playerName;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void prompt() {
        do {
            String command = IOUtils.readLine("Was willst du tun? ");
            if (command.equals("")) {
            } else {
                String[] parsed_command = Textie.parseInput(command);
                if (parsed_command.length < 2) {
                    switch (parsed_command[0].toLowerCase()) {
                        case "hilfe":
                            Textie.printHelp();
                            break;
                        case "speichern":
                            Textie.doSpeichern();
                            break;
                        case "laden":
                            Textie.doLaden();
                            break;
                        default:
                            Textie.printText("Unbekannter Befehl oder fehlende Argumente: " + parsed_command[0]);
                            break;
                    }
                } else {
                    String[] parsed_args = new String[2];
                    if (parsed_command[1] == null) {
                        parsed_args[0] = "nichts";
                    } else {
                        parsed_args = Textie.parseInput(parsed_command[1]);
                    }
                    Textie.executeCommand(parsed_command, parsed_args);
                }
            }
        } while (Dungeon.getDungeon().getCurrentRaum().isLeaveRoom() == false);
    }
}
