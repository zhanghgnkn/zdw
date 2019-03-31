package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread {
	Socket s;
	public ServerReceiverThread(Socket s){
		this.s=s;
	}
	
public  void  run(){
	ObjectInputStream ois;
	ObjectOutputStream oss;
	Message mess;
	while(true){
		try{
			ois=new ObjectInputStream(s.getInputStream());
		    mess=(Message)ois.readObject();
		    System.out.println(mess.getSender()+"¶Ô"+mess.getReceiver()+"Ëµ£º"+mess.getContent());
	    
		    Socket sl=(Socket)StartServer.hmSocket.get(mess.getReceiver());
		    oss=new ObjectOutputStream(s.getOutputStream());
		    oss.writeObject(mess);
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
}
	