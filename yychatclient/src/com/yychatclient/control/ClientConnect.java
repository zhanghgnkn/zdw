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
		s= new Socket("127.0.0.1",3456);//127.0.0.1ָ������ַ���ز��ַ
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }  
   }
   public Message loginValidate(User user){
	   //������������󣬷��Ͷ���	   ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
	   //���������   OutputStream�ֽ������
	   //�ֽ���������󣬰�װ�ɶ��������
	   ObjectOutputStream oos;
	   Message mess=null;
	   ObjectInputStream ois;
	   try {
		oos=new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(user);
		
		//������֤ͨ����mess
		ois=new ObjectInputStream(s.getInputStream());
		mess=(Message)ois.readObject();	
	} catch (IOException | ClassNotFoundException e) {
	
		e.printStackTrace();
	} 
	   return mess;
	   
   }
}