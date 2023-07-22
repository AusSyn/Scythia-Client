package com.client.client.particles;

import java.util.*;

public class ParticleAttachment {

	private static final Map<Integer, int[][]> attachments = new HashMap<>();

	public static int[][] getAttachments(int model) {
        return attachments.get(model);
    }

	static {
		//Completionist cape
		attachments.put(111111, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });
		attachments.put(111111, new int[][] { { 494, 0 }, { 488, 0 }, { 485, 0 }, { 476, 0 }, { 482, 0 }, { 479, 0 }, { 491, 0 } });

		//Trimmed Completionist Cape
		attachments.put(65295, new int[][] { { 494, 1 }, { 488, 1 }, { 485, 1 }, { 476, 1 }, { 482, 1 }, { 479, 1 }, { 491, 1 } });
		attachments.put(65328, new int[][] { { 494, 1 }, { 488, 1 }, { 485, 1 }, { 476, 1 }, { 482, 1 }, { 479, 1 }, { 491, 1 } });
		
		//attachments.put(323, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });

		//Max Cape
		attachments.put(65300, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });
		attachments.put(65322, new int[][] { { 113, 2 }, { 116, 2 }, { 109, 2 }, { 189, 2 }, { 100, 2 }, { 129, 2 }, { 128, 2 }, { 199, 2 }, { 191, 2 }, { 150, 2 }, { 98, 2 }, { 148, 2 } });
		//Master Dung. Cape
		attachments.put(59885, new int[][] { {136, 2 }, { 125, 2 }, { 124, 2 }, { 131, 2 },  {172, 2 }, {144, 2 }, { 143, 2 }, { 142, 2 }, { 161, 2 }, { 123, 2 }, { 89, 2 } });
		attachments.put(59887, new int[][] { {136, 2 }, { 125, 2 }, { 124, 2 }, { 131, 2 },  {172, 2 }, {144, 2 }, { 143, 2 }, { 142, 2 }, { 161, 2 }, { 123, 2 }, { 89, 2 } });
		//ownercape
		attachments.put(41483, new int[][] { {175, 5 }, { 219, 5 }, { 43, 5 }, { 244, 5 },  {243, 5 }, {241, 5 }, { 254, 5 }, { 255, 5 }, { 256, 5 }, { 236, 5 }, { 235, 5 } });
// custom staff
		attachments.put(50847, new int[][] { {1044, 6 }, { 1500, 6 }, { 1504, 6 }, { 1518, 6 },  {1509, 6 }, {1031, 6 }, { 1530, 6 }, { 1536, 6 }, { 1526, 6 }, { 1523, 6}, { 1533, 6 }  });
		//superior wings
		attachments.put(50845, new int[][] { {1537, 7 }, { 1299, 7 }, { 102, 7 }, { 358, 7 },  {128, 7 }, {209, 7 }, {1388, 7}, {1307, 7 } });
		//linear halo
		attachments.put(50779, new int[][] { {149, 8 }, { 51, 8 }, { 41, 8 }, { 53, 8 },  {57, 8 }, {46, 8 }, {78, 8 }, {86, 8 }, {88, 8 }, {104, 8 }, {112, 8 }, {120, 8 }, {0, 8 }, {8, 8 }, {23, 8 }, {16, 8 } });

//mdma Glock off hand
		attachments.put(50902, new int[][] { {1187, 9 }});
//mdma glock main hand
		attachments.put(50901, new int[][] { {1187, 9 }});
//mdma Cape
		attachments.put(50941, new int[][] { {175, 10 }, { 219, 10 }, { 43, 10 }, { 244, 10 },  {243, 10 }, {241, 10 }, { 254, 10 }, { 255, 10 }, { 256, 10 }, { 236, 10 }, { 235, 10 } });
//lorienflux cape
		attachments.put(50951, new int[][] { {175, 11 }, { 219, 11 }, { 43, 11 }, { 244, 11 },  {243, 11 }, {241, 11 }, { 254, 11 }, { 255, 11 }, { 256, 11 }, { 236, 11 }, { 235, 11 } });

		/*attachments.put(41483, new int[][] {
				{13, 3},
				{14, 3},
				{10, 3},
				{8, 3}
		});*/

	}
}