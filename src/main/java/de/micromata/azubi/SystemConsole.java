package de.micromata.azubi;

import java.util.ArrayList;
import java.util.List;

public class SystemConsole implements Console {

	private List<String> cache = new ArrayList<>();

	@Override
	public String read(String outputText) {
		return IOUtils.readLine(outputText);
	}

	@Override
	public void write(String write) {
		cache.add(0, write);
		System.out.println(write);
	}

	@Override
	public List<String> cache() {
		return cache;
	}

	@Override
	public String lastWrite() {
		if (cache.size() > 0) {
			return cache.get(0);
		} else {
			return null;
		}
	}

}
