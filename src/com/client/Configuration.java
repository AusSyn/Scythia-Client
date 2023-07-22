package com.client;

public class Configuration {

	/**
	 * Connection information
	 */
	public static boolean USE_ENCRYPTION = false;//turn this off after models are encrypted
	public static final boolean DECRYPT_MODELS = false;//turn this on to decrypt the models in raw folder
	public static final String ENCRYPTION_KEY = "kjadfkjwengvkjrefkwernfkewjbekwbfewk";
	
	public final static boolean DEVELOPMENT_SERVER = true;
	public static String HOST = DEVELOPMENT_SERVER ? "127.0.0.1" : "51.195.240.178";
	
	public static final int CLIENT_VERSION = 15;
	public static int PORT = DEVELOPMENT_SERVER ? 43594 : 43594;
	
	public final static String CLIENT_NAME = "Solara";
	public static final boolean SEND_HASH = true;
	public static  boolean upscaling = true;
	public static boolean menuHovers = true;
	public static boolean enableNewMenus = false;
	public static final boolean DISCO_ITEMS = false; 
	public final static boolean REPACK_INDICES = false;
	public final static int[] INDICES_TO_REPACK = { 4 };
	
	public static int[] ITEMS_WITH_BLACK = {
			2775, 20142, 20136, 20144, 20136, 20134, 20132, 20146, 20138, 20140, 20650, 20651, 20652, 20653,
			20654, 20128, 20130, 1327, 2349, 2351, 2353, 2355, 2357, 2359,
			2361, 2363, 2619, 2627, 2657, 2665, 2673, 3140, 3486, 6568, 11335, 12158, 1261, 
			12162, 12163, 12164, 12165, 12166, 12167, 12168, 15332, 15333, 15334, 15335,
			13675, 14479, 18510, 19337, 19342, 19347, 19407, 19437, 9921, 9922, 9923, 9924, 9925
	};
	/**
	 * Misc.
	 */
	public final static String JAGGRAB_HOST = "51.195.240.178";
	public static final boolean JAGCACHED_ENABLED = false;

	/**
	 * The NPC bits.
	 * 12 = 317/377
	 * 14 = 474+
	 * 16 = 600+
	 */
	public final static int NPC_BITS = 18;

	public static final int[] packetSizes = {
			0, 0, 0, 1, 6, 0, 0, 0, 4, 0, //0
			0, 2, -1, 1, 1, -1, 1, 2, 0, 0, // 10
			0, 0, 0, 0, 1, 0, 0, -1, 1, 1, //20
			0, 0, 0, 0, -2, 4, 3, 0, 2, 0, //30
			0, 0, 0, 0, 5, 8, 0, 6, 0, 0, //40
			9, 0, 0, -2, 0, 0, 0, 0, 0, 0, //50
			-2, 1, 0, 0, 2, -2, 0, 0, 0, 0, //60
			6, 3, 2, 4, 2, 4, 0, 0, 0, 4, //70
			0, -2, 0, 0, 7, 2, 1, 6, 6, 0, //80
			2, 2, 0, 0, 0, 0, 0, 2, 0, 1, //90
			2, 2, 0, 1, -1, 4, 1, 0, 1, 0, //100
			1, 1, 1, 1, 2, 1, 0, 15, 0, 0, //110
			0, 4, 4, -1, 9, 0, -2, 1, 0, 0, //120 // 9
			-1, 0, 0, 0, 9, 0, 0, 0, 0, 0, // 130
			3, 10, 2, 0, 0, 0, 0, 14, 0, 0, //140
			0, 4, 5, 3, 0, 0, 3, 0, 0, 0, //150
			4, 5, 0, 0, 2, 0, 6, 0, 0, 0, //160
			//0, 3, /*0*/ -1, 0, 5, 7, 10, 6, 5, 1, //170
			0, 3, -2, -2, 5, 5, 10, 6, 5, -2, // 170
			0, 0, 0, 0, 0, 2, 0, -1, 0, 0, //180
			6, 0, 0, 0, 0, 2, -1, 0, -1, 0, //190
			4, 0, 0, 3, 0, -1, 3, 16, 4, 4,  //200
			4, 0, 0, 0, -1, 7, 0, -2, 2, 0, //210
			0, 1, -2, -2, 0, 0, 0, 0, 0, 0, // 220
			8, 0, 3, 0, 0, 0, 0, 0, 0, 0,//230
			2, -2, 0, 0, -1, 0, 6, 0, 4, 3,//240
			-1, 0, 0, -1, 6, 0, 0//250
	};

}
