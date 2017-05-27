package ru.spbau.mit.screens;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asciiPanel.AsciiPanel;
import ru.spbau.mit.factory.ItemFactory;
import ru.spbau.mit.inventory.Item;
import ru.spbau.mit.mob.Mob;
import ru.spbau.mit.factory.MobFactory;
import ru.spbau.mit.mob.Player;
import ru.spbau.mit.world.World;
import ru.spbau.mit.world.WorldBuilder;

public class PlayScreen implements Screen {
	private World world;
	private Player player;
	private int screenWidth;
	private int screenHeight;
	private ArrayList<String> messages;
	
	public PlayScreen(){
		screenWidth = 80;
		screenHeight = 23;
		messages = new ArrayList<>();
		createWorld();
		MobFactory mobFactory = new MobFactory(world);
		createMobs(mobFactory);
		ItemFactory itemFactory = new ItemFactory(world);
		createItems(itemFactory);
	}

	private void createItems(ItemFactory itemFactory) {
		Random rnd = new Random();
		for(int i = 0; i < 15; i++) {
			int idx = rnd.nextInt(itemFactory.getItemTypes().size());
			Item item = itemFactory.getItem(itemFactory.getItemTypes().get(idx));
			world.registerItem(item);
		}
	}
	private void createMobs(MobFactory mobFactory){
		Random rnd = new Random();
		player = (Player) mobFactory.getMob("player", messages);
		world.registerMob(player);
		for (int i = 0; i < 10; i++){
			int idx = rnd.nextInt(mobFactory.getMobTypes().size());
			Mob m = mobFactory.getMob(mobFactory.getMobTypes().get(idx), messages);
			world.registerMob(m);
		}
	}

	private void createWorld() {
		world = new WorldBuilder(90, 32)
				.makeCaves()
				.build();
	}
	
	public int getScrollX() { return Math.max(0, Math.min(player.getPosition().getX() - screenWidth / 2, world.width() - screenWidth)); }
	
	public int getScrollY() { return Math.max(0, Math.min(player.getPosition().getY() - screenHeight / 2, world.height() - screenHeight)); }

	/**
	 * Drawing the game scene
	 * */
	@Override
	public void displayOutput(AsciiPanel terminal) {
		int left = getScrollX();
		int top = getScrollY(); 
		
		displayTiles(terminal, left, top);
		displayMessages(terminal, messages);
		
		terminal.write("--press [escape] to lose--", 50, 23);

		String stats = String.format(
				" %3d/%3d hp %3d dmg %3d def",
				player.getAttr().getCurrentHP(),
				player.getAttr().getMaxHP(),
				player.getAttr().getAttackValue(),
				player.getAttr().getDefenseValue());
		terminal.write(stats, 1, 23);
	}

	private void displayMessages(AsciiPanel terminal, List<String> messages) {
		int top = screenHeight - messages.size();
		for (int i = 0; i < messages.size(); i++){
			terminal.writeCenter(messages.get(i), top + i);
		}
		messages.clear();
	}

	private void displayTiles(AsciiPanel terminal, int left, int top) {
		for (int x = 0; x < screenWidth; x++){
			for (int y = 0; y < screenHeight; y++){
				int wx = x + left;
				int wy = y + top;

				Mob mob = world.checkMob(wx, wy);
				if (mob != null) {
					terminal.write(
							mob.getAttr().getGlyph(),
							mob.getPosition().getX() - left,
							mob.getPosition().getY() - top,
							mob.getAttr().getColor());
					continue;
				}
				Item item = world.checkItem(wx, wy);
				if (item != null) {
					terminal.write(
							item.getGlyph(),
							item.getPos().getX() - left,
							item.getPos().getY() - top,
							item.getColor());
					continue;
				}
				terminal.write(world.glyph(wx, wy), x, y, world.color(wx, wy));
			}
		}
	}
	/**
	 * Keyboard processor
	 * */
	@Override
	public Screen respondToUserInput(KeyEvent key) {
		switch (key.getKeyCode()){
		case KeyEvent.VK_ESCAPE: return new LoseScreen();
		case KeyEvent.VK_LEFT: player.moveBy(-1, 0); break;
		case KeyEvent.VK_RIGHT: player.moveBy( 1, 0); break;
		case KeyEvent.VK_UP: player.moveBy( 0,-1); break;
		case KeyEvent.VK_DOWN: player.moveBy( 0, 1); break;
		case KeyEvent.VK_A: player.addItem(world.checkItem(player.getPosition().getX(), player.getPosition().getY()));
		}
		world.update();
		if (player.getAttr().getCurrentHP()  < 1)
			return new LoseScreen();
		if (world.getCountMob() == 1) {
			return new WinScreen();
		}
		return this;
	}
}
