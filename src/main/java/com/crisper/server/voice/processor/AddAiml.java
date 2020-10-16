package com.crisper.server.voice.processor;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.configuration.BotConfiguration;
//import org.alicebot.ab.MagicBooleans;

public class AddAiml {

	private static final boolean TRACE_MODE = false;
	static String botName = "crisper";

	public static void main(String[] args) {
		try {

			String resourcesPath = getResourcesPath();
			System.out.println(resourcesPath);
			Bot bot = new Bot(BotConfiguration.builder().name("crisper").path(resourcesPath).build());
			
			bot.writeAIMLFiles();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getResourcesPath() {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		path = path.substring(0, path.length() - 2);
		System.out.println(path);
		String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
		return resourcesPath;
	}

}
