package de.micromata.azubi;

import java.util.List;

public interface Console {
	public String read(String outputText);
	public void write(String write);
	public List<String> cache();
	public String lastWrite();
}
