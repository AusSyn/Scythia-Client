package wycody.effects.impl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import wycody.effects.Effect;
import wycody.effects.EffectStatus;



/**
 * Created at: Jan 22, 2017 3:09:28 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class FadeEffect extends Effect {

	/**
	 * The start time.
	 */
	private long start;

	/**
	 * The fade duration.
	 */
	private long duration;

	/**
	 * Constructs a new {@link FadeEffect} class object instance.
	 * 
	 * @param source
	 *            the source image.
	 * @param target
	 *            the target image.
	 * @param duration
	 *            the fade duration time in milliseconds.
	 */
	public FadeEffect(BufferedImage source, BufferedImage target, long duration) {
		super(source, target);
		this.duration = duration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.effects.Effect#start()
	 */
	@Override
	public void start() {
		checkStatus(EffectStatus.NONE);
		start = System.currentTimeMillis();
		status = EffectStatus.RUNNING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.effects.Effect#step()
	 */
	@Override
	public void step() {
		checkStatus(EffectStatus.RUNNING);
		if (System.currentTimeMillis() >= start + duration) {
			finish();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.effects.Effect#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {
		g = (Graphics2D) g.create();
		/* the alpha source composite value */
		float alpha = (float) (System.currentTimeMillis() - start) / (float) duration;
		g.setComposite(AlphaComposite.SrcOver.derive(alpha));
		g.drawImage(source, 0, 0, null);
		g.setComposite(AlphaComposite.SrcOver.derive(1f - alpha));
		g.drawImage(target, 0, 0, null);
		g.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wycody.effects.Effect#finish()
	 */
	@Override
	public void finish() {
		checkStatus(EffectStatus.RUNNING);
		start = -1;
		status = EffectStatus.FINISHED;
	}

}
