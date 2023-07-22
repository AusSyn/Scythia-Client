package wycody.effects;

import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created at: Jan 22, 2017 3:03:29 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class EffectManager {

	/**
	 * The running effects.
	 */
	private Queue<Effect> effects;

	/**
	 * The current effect.
	 */
	private Effect current;

	/**
	 * Constructs a new {@link EffectManager} class object instance.
	 */
	public EffectManager() {
		effects = new ArrayDeque<Effect>();
	}

	/**
	 * Adds the specified {@code effect} to the manager.
	 * 
	 * @param effect
	 *            the effect to add.
	 */
	public void add(Effect effect) {
		if (effects.contains(effect))
			throw new IllegalArgumentException("The effect was already added to this manager.");
		effects.add(effect);
	}

	/**
	 * Starts the effects manager.
	 */
	public void start() {
		if (current != null)
			return;
		next();
	}

	public void process(Graphics2D g) {
		next();
		if (current != null) {
			step();
			if (current.getStatus() == EffectStatus.RUNNING) {
				draw(g);
			}
		}

	}

	/**
	 * Process a single step to all effects.
	 */
	private void step() {
		next();
		if (current != null) {
			current.step();
		}
	}

	/**
	 * Draws the current effects state to the given buffer.
	 * 
	 * @param g
	 *            the current effects state.
	 */
	private void draw(Graphics2D g) {
		if (current != null) {
			current.draw(g);
		}
	}

	/**
	 * Checks if we need to move to the next effect then move.
	 */
	private void next() {
		if (current == null || current.getStatus() == EffectStatus.FINISHED) {
			if (effects.size() > 0) {
				current = effects.poll();
				current.start();
			} else {
				current = null;
			}
		}
	}

	/**
	 * Checks if the manager is currently running any effect.
	 * 
	 * @return <code>true</code> if there is currently effects running otherwise
	 *         <code>false</code>.
	 */
	public boolean isRunning() {
		next();
		return current != null;
	}
}
