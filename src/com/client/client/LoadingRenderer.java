package com.client.client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import wycody.effects.EffectManager;
import wycody.effects.impl.FadeInEffect;
import wycody.effects.impl.FadeOutEffect;



public class LoadingRenderer implements Runnable {

	/**
	 * The rendering background.
	 */
	private static Image background;

	/**
	 * The empty bar image.
	 */
	private static Image barEmpty;

	/**
	 * The full bar image.
	 */
	private static Image barFull;

	/**
	 * The text font.
	 */
	private static Font textFont;

	static {
		try {
			background = ImageIO.read(GameClient.class.getResourceAsStream("/resources/background.gif"));
			barEmpty = ImageIO.read(GameClient.class.getResourceAsStream("/resources/barEmpty.png"));
			barFull = ImageIO.read(GameClient.class.getResourceAsStream("/resources/barFull.png"));
			textFont = new Font("Monospaced", 0, 14);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The client instance.
	 */
	private GameClient c;

	/**
	 * The graphics tool.
	 */
	private Graphics g;

	/**
	 * The back image.
	 */
	private BufferedImage buffer;

	/**
	 * The current percentage.
	 */
	private int percentage;

	/**
	 * The fade effect handler.
	 */
	private EffectManager effect;

	/**
	 * Construct a new {@link LoadingRenderer} object instance.
	 * 
	 * @param c
	 *            the client instance.
	 * @param g
	 *            the graphics tool.
	 */
	public LoadingRenderer(GameClient c, Graphics g) {
		this.c = c;
		this.g = g;
		buffer = new BufferedImage(765, 503, BufferedImage.TYPE_INT_RGB);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (c.isLoading) {
			long frameStart = System.currentTimeMillis();
			synchronized (this) {
				try {
					if (percentage < GameClient.loadingPercentage)
						percentage++;
					render();
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
			long frameEnd = System.currentTimeMillis();
			int spaceTime = (int) (20 - (frameEnd - frameStart));
			if (spaceTime > 0) {
				try {
					Thread.sleep(spaceTime);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	/**
	 * Renders the loading screen.
	 */
	public void render() {
		Graphics back = buffer.getGraphics();
		back.drawImage(background, 0, 0, null);
		back.drawImage(barEmpty, 198, 246, null);
		int fillWidth = (int) (percentage / 101D * barFull.getWidth(null));
		if (fillWidth > 0) {
			Image cutted = Sprite.getCutted(barFull, 0, 0, fillWidth, barFull.getHeight(null));
			// performs better if we use the Graphics2D method.
			Graphics2D g2 = (Graphics2D) back;
			g2.drawImage(cutted, AffineTransform.getTranslateInstance(202, 251), null);
		}
		String loadText = GameClient.loadingText;
		back.setFont(textFont);
		back.setColor(Color.WHITE);
		back.drawString(loadText, GameClient.clientWidth / 2 - back.getFontMetrics().stringWidth(loadText) / 2, 255);
		try {
			c.drawLoginScreen();
		} catch (Exception e) {

		}
		if (effect == null && GameClient.hasLoaded) {
			effect = new EffectManager();
			effect.add(new FadeInEffect(buffer, Color.BLACK, 2500));
			effect.add(new FadeOutEffect((BufferedImage) c.titleScreen.image, Color.BLACK, 1500));
			effect.start();
		} else if (effect != null) {
			if (effect.isRunning()) {
				effect.process((Graphics2D) g.create());
			} else {
				effect = null;
				c.isLoading = false;
			}
		} else {
			g.drawImage(buffer, 0, 0, null);
		}
	}
}