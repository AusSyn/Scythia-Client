package wycody.effects.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;

import wycody.effects.EffectUtil;


/**
 * Created at: Jan 22, 2017 3:26:42 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class FadeInEffect extends FadeEffect {

	/**
	 * Constructs a new {@link FadeInEffect} class object instance.
	 * 
	 * @param source
	 *            the source image.
	 * @param target
	 *            the fade color.
	 * @param duration
	 *            the duration time.
	 */
	public FadeInEffect(BufferedImage source, Color target, long duration) {
		super(EffectUtil.createEmptyImage(source.getWidth(), source.getHeight(), target), source, duration);
	}

}
