package wycody.maths;

/**
 * Created at: Jan 22, 2017 9:39:01 AM
 * 
 * @author Walied-Yassen A.K.A Cody
 */
public class CircleAngleTable implements Maths {

	public static final int[] SINE = new int[16384];
	public static final int[] COSINE = new int[16384];

	static {
		double d = 3.834951969714103E-4;
		for (int i = 0; i < 16384; i++) {
			SINE[i] = (int) (16384.0 * Math.sin(i * d));
			COSINE[i] = (int) (16384.0 * Math.cos(i * d));
		}
	}
}