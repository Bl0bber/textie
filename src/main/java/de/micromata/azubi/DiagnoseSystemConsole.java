package de.micromata.azubi;

import de.micromata.azubi.model.Dungeon;

public class DiagnoseSystemConsole extends SystemConsole {

	private Dungeon dungeon;
	
	public DiagnoseSystemConsole() {
	}
	
	public DiagnoseSystemConsole(Dungeon dungeon) {
		this();
		this.dungeon = dungeon;
	}
	
	public void setDungeon(Dungeon context) {
		this.dungeon = context;
	}
	
	public void write(final String text) {
		String modText = text;
		if (this.dungeon != null) {
			modText = dungeon.getCurrentRaum() == null ? text : "[" + dungeon.getCurrentRaum().getRoomNumber() + "], " + text;
		}
		
		super.write(modText);
	}
}
