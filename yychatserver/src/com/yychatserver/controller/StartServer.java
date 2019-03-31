package com.yychatserver.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.yychat.model.Message;
import com.yychat.model.User;

public class StartServer {
	public static HashMap hmSocket=new HashMap<String,Socket>();
	private static final String User = null;
	ServerSocket ss;
	Socket s;
	String userName;
	String passWord;
	public StartServer(){
	    try {//捕获异常  这里捕获的异常必须要处理
			ss=new ServerSocket(3456);
			System.out.println("服务器已经启动，监听3456端口");//端口必须要在1024以上
			while(true){//死循环
				s= ss.accept();	
				System.out.println("连接成功"+s); 
				
				//接收对象
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User user=(User)ois.readObject();
				this.userName=user.getUserName();
				this.passWord=user.getPassWord();
				System.out.println(userName);
				System.out.println(passWord);
				
				
				//实现密码验证功能
				Message mess=new Message();
			     mess.setSender("Server");
			     mess.setReceiver(userName);
				if(passWord.equals("123456")){//对象比较
				
					//告诉客户端密码验证通过的消息，可以创建一个Message类
				     mess.setMessageType(Message.message_LoginSuccess);//"1"为验证通过
					
				}else{
					mess.setMessageType(Message.message_LoginFailure);
				}
				ObjectOutputStream oss=new ObjectOutputStream(s.getOutputStream());
				oss.writeObject(mess);
				
				
				//不可以在这里接收聊天信息，应该要新建一个接收线程
				if(passWord.equals("123456")){
					hmSocket.put(userName,s);
					new ServerReceiverThread(s).start();//
				}		
			}
			
	    } catch (IOException e) {//这个代码不一定会使用
			e.printStackTrace();//处理异常printStackTrace打印出你的异常的地址信息
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	}