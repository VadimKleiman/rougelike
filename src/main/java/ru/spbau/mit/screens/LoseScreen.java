package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class LoseScreen implements Screen {

	@Override
	public void displayOutput(AsciiPanel terminal) {
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 - 2);
		terminal.writeCenter("You lost!", terminal.getHeightInCharacters() / 2);
		terminal.writeCenter("============================", terminal.getHeightInCharacters() / 2 + 2);
		terminal.writeCenter("-- press [enter] to restart --", 22);
	}

	@Override
	public Screen respondToUserInput(KeyEvent key) {
		return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
	}
}
