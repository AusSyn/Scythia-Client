package wycody.effects;

/**
 * Created at: Jan 22, 2017 3:09:55 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public enum EffectStatus {

	/**
	 * the effect is currently not started.
	 */
	NONE,

	/**
	 * The effect is currently running.
	 */
	RUNNING,

	/**
	 * The effect has finished, waiting to get removed.
	 */
	FINISHED,
}
