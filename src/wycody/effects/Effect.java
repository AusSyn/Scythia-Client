package wycody.effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Created at: Jan 22, 2017 2:58:52 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public abstract class Effect {

	/**
	 * The source image.
	 */
	protected BufferedImage source;

	/**
	 * The target image.
	 */
	protected BufferedImage target;

	/**
	 * The effect status.
	 */
	protected EffectStatus status;

	/**
	 * Constructs a new {@link Effect} class object instance.
	 * 
	 * @param source
	 */
	public Effect(BufferedImage source, Color target) {
		this(source, EffectUtil.createEmptyImage(source.getWidth(), source.getHeight(), target));
	}

	/**
	 * Constructs a new {@link Effect} class object instance.
	 * 
	 * @param source
	 *            the source image.
	 * @param target
	 *            the target image.
	 */
	public Effect(BufferedImage source, BufferedImage target) {
		this.source = source;
		this.target = target;
		status = EffectStatus.NONE;
	}

	/**
	 * Starts the effect.
	 */
	public abstract void start();

	/**
	 * Process a single step.
	 */
	public abstract void step();

	/**
	 * Draws the current effect state to the given graphics.
	 * 
	 * @param g
	 *            the graphics to draw to.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Finishes the effect.
	 */
	public abstract void finish();

	/**
	 * Checks for the effect status.
	 * 
	 * @param status
	 *            the effect status.
	 */
	protected void checkStatus(EffectStatus status) {
		if (this.status != status) {
			throw new IllegalStateException(
					"The event needs to be at status " + status + " before performing this action.");
		}
	}

	/**
	 * Gets the effect status.
	 * 
	 * @return the effect status.
	 */
	public EffectStatus getStatus() {
		return status;
	}
}
