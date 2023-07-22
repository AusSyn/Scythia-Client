package com.client.client.cache;

import com.client.client.GameClient;
import com.client.client.signlink;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CacheDownloader implements Runnable {

	public static final String CACHE_PATH = System.getProperty("user.home") + File.separator + ".solara" + File.separator;
	private static final String ZIP_URL = "https://www.dropbox.com/s/8wd110yhplmebph/.solara.zip?dl=1";
	private static final String VERSION_FILE = CACHE_PATH + "version.txt";
	private static final int CACHE_VERSION = 80;

	private CacheDownloader.GUI g;

	@SuppressWarnings("resource")
	public long getCurrentVersion() {
		try {
			File versionDir = new File(VERSION_FILE);

			if (!versionDir.exists()) {
				versionDir.createNewFile();
				return -1;
			}

			return Long.parseLong(new BufferedReader(new InputStreamReader(new FileInputStream(VERSION_FILE))).readLine());
		} catch (Exception e) {
			return -1;
		}
	}

	private void handleException(Exception e) {
		StringBuffer strBuff = new StringBuffer();

		strBuff.append("Please Screenshot this message, and send it to an admin!\r\n\r\n");
		strBuff.append(e.getClass().getName() + " \"" + e.getMessage() + "\"\r\n");

		for (StackTraceElement s : e.getStackTrace()) {
			strBuff.append(s.toString() + "\r\n");
		}
	}

	@Override
	public void run() {
		try {
			long newest = CACHE_VERSION;
			long current = getCurrentVersion();

			if (newest != current) {
				updateCache();
				new FileOutputStream(VERSION_FILE).write(String.valueOf(newest).getBytes());
			}
		} catch (Exception e) {
			handleException(e);
		}
	}


	private void updateCache() {
		File clientZip = downloadCache();

		if (clientZip != null) {
			unZip(clientZip);
		}
	}

	private void unZip(File clientZip) {
		try {
			unZipFile(clientZip, new File(signlink.findcachedir()));
			Files.delete(clientZip.toPath());
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void unZipFile(File zipFile, File outFile) throws IOException {
		GameClient.instance.setLoadingText(1,"Extracting..");
		ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

		ZipEntry e;

		long max = 0;
		long curr = 0;

		while ((e = zin.getNextEntry()) != null) {
			max += e.getSize();
		}

		zin.close();

		ZipInputStream in = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));

		while ((e = in.getNextEntry()) != null) {
			if (e.isDirectory()) {
				new File(outFile, e.getName()).mkdirs();
			} else {
				FileOutputStream out = new FileOutputStream(new File(outFile, e.getName()));

				byte[] b = new byte[1024];

				int len;

				while ((len = in.read(b, 0, b.length)) > -1) {
					curr += len;
					out.write(b, 0, len);
				}

				out.flush();
				out.close();
			}
		}

		in.close();
	}

	private File downloadCache() {
		File ret = new File(CACHE_PATH + "solara.zip");

		try (OutputStream out = new FileOutputStream(ret)) {
			URLConnection conn = new URL(ZIP_URL).openConnection();
			InputStream in = conn.getInputStream();

			long curr = 0;
			byte[] b = new byte[1024];
            int length = conn.getContentLength();
			int len;
			long startTime = System.currentTimeMillis();

			while ((len = in.read(b, 0, b.length)) > -1) {
				out.write(b, 0, len);
				curr += len;
                int percentage = (int)(((double)curr / (double)length) * 100D);
				int downloadSpeed = (int) ((curr / 1024) / (1 + ((System.currentTimeMillis() - startTime) / 1000)));
                GameClient.instance.setLoadingText(0,downloadSpeed + "kbps- " + percentage + "%");
			}

			out.flush();
			in.close();
			return ret;
		} catch (Exception e) {
			handleException(e);
			ret.delete();
			return null;
		}
	}

	public class GUI extends JFrame {
		private static final long serialVersionUID = 1L;

		/** Creates new form GUI */
		public GUI() {

		}

		private int percent = 0;

		public int getPercent() {
			return percent;
		}

	}
}