package info.shuige.neonat.ui.controler;

import java.net.ServerSocket;
import java.net.Socket;

public class ConsoleManager implements Runnable {

	
	static final int SOCKET_TIME=60000;
	private ServerSocket server;
	
	public ConsoleManager(){
		try{
			server = new ServerSocket(23);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true){
			try{
				Socket socket = server.accept();
				socket.setSoTimeout(SOCKET_TIME);
				new Thread(new ConsoleSession(socket),"Neonat BBS Session").start();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
