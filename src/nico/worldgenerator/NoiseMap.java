package nico.worldgenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import org.j3d.texture.procedural.PerlinNoiseGenerator;

public class NoiseMap extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 675;
	
	private PerlinNoiseGenerator noiseGenerator;
	private float[][] noiseMap;
	
	public static final float FREQUENCY = 7.5f;
	public static final int OCTAVES = 8;
	public static final float AMPLITUDE = 12.0f;
	
	public NoiseMap() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.noiseGenerator = new PerlinNoiseGenerator();
		this.noiseMap = new float[WIDTH][HEIGHT];
		this.generateMap();
	}
	
	private void generateMap() {
		for(int x=0;x<WIDTH;x++) {
			for(int y=0;y<HEIGHT;y++) {
				this.noiseMap[x][y] = this.generateNoise(x / (float) HEIGHT, y / (float) HEIGHT);
			}
		}
	}
	
	private float generateNoise(float x, float y) {
		float value = 0;
		
		for(int i=0;i<OCTAVES;i++) {
			float xn = x * FREQUENCY * (float) Math.pow(2, i);
			float yn = y * FREQUENCY * (float) Math.pow(2, i);
			value += this.noiseGenerator.noise2(xn, yn) * AMPLITUDE * Math.pow(2, -i);
		}
		
		return value;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for(int x=0;x<WIDTH;x++) {
			for(int y=0;y<HEIGHT;y++) {
				g.setColor(pickColor(noiseMap[x][y]));
				g.fillRect(x, y, 1, 1);
			}
		}
		
		super.repaint();
	}
	
	private Color pickColor(float noiseValue) {
		if(noiseValue <= -3.0f)
			return new Color(61, 94, 115);
		else if(noiseValue > -3.0f && noiseValue <= -0.2f)
			return new Color(85, 136, 167);
		else if(noiseValue > -0.2f && noiseValue <= 0.1f)
			return new Color(191, 169, 140);
		else if(noiseValue > 0.1f && noiseValue <= 2.0f)
			return new Color(106, 117, 80);
		else if(noiseValue > 2.0f && noiseValue <= 4.0f)
			return new Color(94, 108, 62);
		else if(noiseValue > 4.0f && noiseValue <= 6.0f)
			return new Color(123, 123, 116);
		else if(noiseValue > 6.0f && noiseValue <= 8.0f)
			return new Color(157, 145, 138);
		else if(noiseValue > 8.0f && noiseValue <= 10.0f)
			return new Color(228, 228, 227);
		else
			return Color.WHITE;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		this.noiseGenerator = new PerlinNoiseGenerator();
		this.noiseMap = new float[WIDTH][HEIGHT];
		this.generateMap();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}