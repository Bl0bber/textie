package de.micromata.azubi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lukas F&uuml;lling (l.fuelling@micromata.de)
 * @author Julian Siebert (j.siebert@micromata.de)
 */
public class Raum implements Serializable{
    private static final long serialVersionUID = -2269575363024102428L;
    protected List<Item> items = new ArrayList<Item>();
    protected boolean falltuerUsed = false;
    protected int roomNumber;
    protected String willkommensNachricht;
    protected Map<Richtung, Raum> verbindungen = new HashMap<>();
    protected boolean leaveRoom = false;
    protected Inventory inventory;
    private Human human;
    private ArrayList<Door> doors;

    public Raum() {
    }

  /**
   *
   * @param number The number of the room
   * @param willkommensNachricht The message you get, when you enter the room.
   */
    public Raum(int number, String willkommensNachricht) {
        this.roomNumber = number;
        this.willkommensNachricht = willkommensNachricht;
    }

  /**
   *
   * @param item The Item you search
   * @return True if the item is in there.
   * @deprecated
   */
    public boolean hasItem(Item item) {
        if (items.contains(item)) {
            return true;
        }
        return false;
    }

  /**
   *
   * @return The room number.
   */
    public int getNumber() {
        return roomNumber;
    }

  /**
   *
   * @return The Room number as a string.
   */
    public String getNumberAsString() {
        return String.valueOf(this.roomNumber);
    }

  /**
   * @param obj An Object
   * @return True if the object is the same.
   */
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

  /**
   * Starts a room.
   * @param withPrompt Set to <code>true</code>, if you want a prompt.
   */
    public void start(boolean withPrompt) {

        if (roomNumber == 3) {
            ToggleItem fackel = (ToggleItem) inventory.findItemByName("Fackel");
            if (fackel.getState() == true) {
                Textie.printText("Ein Windstoß sorgt dafür, dass die Fackel ausgeht.");
                fackel.setState(false);
            }
        }
        Textie.printText(willkommensNachricht);
        Textie.warten(withPrompt);
    }

  /**
   *
   * @return The room number
   */
    public int getRoomNumber() {
        return roomNumber;
    }

  /**
   *
   * @return The intro message.
   */
    public String getWillkommensNachricht() {
        return willkommensNachricht;
    }

  /**
   * Get the next room.
   * @param door The door in the current room you want to going trough
   * @return The next room.
   */
    public Raum getNextRoom(Door door) {
        return Dungeon.getDungeon().findRaumByNummer(door.raumNr);
    }

  /**
   * Is the room being leaved?
   * @return True if the room is to be leaved.
   */
    public boolean isLeaveRoom() {
        return leaveRoom;
    }

  /**
   *
   * @param leaveRoom you want to go?
   */
    public void setLeaveRoom(boolean leaveRoom) {
        this.leaveRoom = leaveRoom;
    }

  /**
   *
   * @param verbindungen Set some connections.
   */
    public void setVerbindungen(Map<Richtung, Raum> verbindungen) {
        this.verbindungen = verbindungen;
    }

  /**
   *
   * @return The inventory of the room.
   */
    public Inventory getInventory() {
        return inventory;
    }

  /**
   *
   * @param inventory The inventory of the room.
   */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Places a human into the room
     * @param human The Human you want to place in the Room
     */
    public void setHuman(Human human) {
        this.human = human;
    }

    /**
     *
     * @return Returns the Human in the room
     */
    public Human getHuman() {
        return human;
    }

    /**
     *
     * @param richtung The direction you want to go
     * @return The door which is in the direction you asked
     */
    public Door findDoorByDirection(Richtung richtung) {

        for (Door door : this.doors) {
            if (door.richtungRaum1.equals(richtung)){
                return door;
            }
        }
        return null;
    }

    public void setDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }
}
