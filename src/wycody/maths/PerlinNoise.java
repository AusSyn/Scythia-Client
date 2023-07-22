package wycody.maths;

public class PerlinNoise {

	public static final int calculate(int x, int y) {
		int noise = interpolatedNoise(x + 45365, y + 91923, 4) - 128
				+ (interpolatedNoise(x + 10294, y + 37821, 2) - 128 >> 1) + (interpolatedNoise(x, y, 1) - 128 >> 2);
		noise = 35 + (int) (noise * 0.3);
		if (noise < 10) {
			noise = 10;
		} else if (noise > 60) {
			noise = 60;
		}
		return noise;
	}

	private static int interpolatedNoise(int x, int y, int scale) {
		int ix = x / scale;
		int rx = x & scale - 1;
		int iy = y / scale;
		int ry = y & scale - 1;
		int n1 = smoothNoise(ix, iy);
		int n2 = smoothNoise(ix + 1, iy);
		int n3 = smoothNoise(ix, iy + 1);
		int n4 = smoothNoise(ix + 1, iy + 1);
		int p1 = interpolate(n1, n2, rx, scale);
		int p2 = interpolate(n3, n4, rx, scale);
		return interpolate(p1, p2, ry, scale);
	}

	public static int interpolate(int x, int y, int point, int scale) {
		int v = 65536 - CircleAngleTable.COSINE[point * 8192 / scale] >> 1;
		return ((65536 - v) * x >> 16) + (y * v >> 16);
	}

	private static int smoothNoise(int x, int y) {
		int corners = noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(1 + x, 1 + y);
		int sides = noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, 1 + y);
		int center = noise(x, y);
		return sides / 8 + corners / 16 + center / 4;
	}

	private static int noise(int x, int y) {
		int n = x + y * 57;
		n = n << 13 ^ n;
		int p = 1376312589 + (789221 + 15731 * n * n) * n & 0x7fffffff;
		return p >> 19 & 0xff;
	}

}
