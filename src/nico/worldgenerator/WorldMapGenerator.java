package nico.worldgenerator;

import javax.swing.JFrame;

public class WorldMapGenerator {

	private JFrame window;
	private NoiseMap map;
	
	private void start() {
		this.createWindow();
		this.createWorld();
		this.window.setVisible(true);
	}
	
	private void createWindow() {
		this.window = new JFrame("World Map Generator");
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.window.setResizable(false);
		this.window.setBounds(10, 10, 0, 0);
	}
	
	private void createWorld() {
		this.map = new NoiseMap();
		this.map.setFocusable(true);
		this.map.addKeyListener(map);
		this.window.add(map);
		this.map.requestFocusInWindow();
		this.window.pack();
	}
	
	public static void main(String[] args) {
		new WorldMapGenerator().start();
	}
}
