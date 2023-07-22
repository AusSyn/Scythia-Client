package wycody.maths;

/**
 * Created at: Jan 22, 2017 9:38:28 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public interface Maths {

	/**
	 * The PI constant value.
	 */
	double PI = Math.PI;

	/**
	 * The half PI constant value.
	 */
	double HALF_PI = PI / 2;

	/**
	 * The phase resoultion bits count.
	 */
	byte PHASE_BITS = 14;

	/**
	 * The angle phase resolution.
	 */
	short PHASE_RESOLUTION = 2 << PHASE_BITS - 1;

	/**
	 * The radians per angle step.
	 */
	double RADIANS_PER_STEP = 2 * PI / PHASE_RESOLUTION;

	/**
	 * The steps per single radian.
	 */
	double STEPS_PER_RADIAN = PHASE_RESOLUTION / (2 * PI);
}
