package com.crisper.server.voice.processor;

import java.io.File;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.alicebot.ab.configuration.BotConfiguration;
import org.alicebot.ab.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class Chatbot {
	private static final boolean TRACE_MODE = false;
	static String botName = "crisper";
	Chat chatSession;
	@Autowired
	VoiceActionProcessorService voice;

	@PostConstruct
	public  void init() {
		new Thread(){
			@Override
			public void run() {
				try {
					Scanner s=new Scanner(System.in);
					String resourcesPath = getResourcesPath();
					System.out.println(resourcesPath);
					Bot bot = new Bot(BotConfiguration.
							builder()
							.name(botName)
							.path(resourcesPath)
							.enableNetworkConnection(false).build());
					chatSession = new Chat(bot);
					bot.getBrain().nodeStats();
					String textLine = "";
					while(true) {
						System.out.print("Human : ");
						textLine = s.nextLine();
						if ((textLine == null) || (textLine.length() < 1))
							System.out.println("null");
						if (textLine.equals("q")) {
							System.exit(0);
						} else if (textLine.equals("wq")) {
							bot.writeQuit();
							System.exit(0);
						} else {
							String request = textLine;
							if (false)
								System.out.println("STATE=" + request + ":THAT=" + ((History) chatSession.getThatHistory().get(0)).get(0) + ":TOPIC=" + chatSession.getPredicates().get("topic"));
							String response = chatSession.multisentenceRespond(request);
							while (response.contains("&lt;"))
								response = response.replace("&lt;", "<");
							while (response.contains("&gt;"))
								response = response.replace("&gt;", ">");
							System.out.println("Robot : " + response);
							try{
								voice.say(response);
							}catch (UnknownHostException ex){
								voice.say(response);
							}
							catch (Exception e){
								e.printStackTrace();
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void command(String request) throws Exception {
		System.out.println(request);
		String response = chatSession.multisentenceRespond(request);
		voice.say(response);
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
