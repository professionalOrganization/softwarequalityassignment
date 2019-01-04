package hanze.nl.infobord;

import org.apache.log4j.BasicConfigurator;

public class Runner {
	
	public static void thread(Runnable runnable, boolean daemon) {
		Thread brokerThread = new Thread(runnable);
		brokerThread.setDaemon(daemon);
		brokerThread.start();
	}

    public static void main(String[] args) throws Exception {
        thread(new ListenerStarter("(HALTE = 'G') AND (RICHTING = '1')"),false);
    }
}