package com.yychatclient.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychat.model.User;

public class ClientConnect {
	public static Socket s;
				
   public ClientConnect(){
	  try {
		s= new Socket("127.0.0.1",3456);//127.0.0.1指本机地址，回测地址
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }  
   }
   public Message loginValidate(User user){
	   //输入输出流对象，发送对象	   ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
	   //对象输出流   OutputStream字节输出流
	   //字节输出流对象，包装成对象输出流
	   ObjectOutputStream oos;
	   Message mess=null;
	   ObjectInputStream ois;
	   try {
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(user);
		
		//接收验证通过的mess
		ois=new ObjectInputStream(s.getInputStream());
		mess=(Message)ois.readObject();	
	} catch (IOException | ClassNotFoundException e) {
	
		e.printStackTrace();
	} 
	   return mess;
	   
   }
}
