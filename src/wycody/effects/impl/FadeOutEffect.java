package wycody.effects.impl;

import java.awt.Color;
import java.awt.image.BufferedImage;

import wycody.effects.EffectUtil;


/**
 * Created at: Jan 22, 2017 3:27:35 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class FadeOutEffect extends FadeEffect {

	/**
	 * Constructs a new {@link FadeOutEffect} class object instance.
	 * 
	 * @param source
	 *            the source image.
	 * @param target
	 *            the fade color.
	 * @param duration
	 *            the duration time.
	 */
	public FadeOutEffect(BufferedImage source, Color target, long duration) {
		super(source, EffectUtil.createEmptyImage(source.getWidth(), source.getHeight(), target), duration);
	}

}
