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
	    try {//�����쳣  ���ﲶ����쳣����Ҫ����
			ss=new ServerSocket(3456);
			System.out.println("�������Ѿ�����������3456�˿�");//�˿ڱ���Ҫ��1024����
			while(true){//��ѭ��
				s= ss.accept();	
				System.out.println("���ӳɹ�"+s); 
				
				//���ն���
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User user=(User)ois.readObject();
				this.userName=user.getUserName();
				this.passWord=user.getPassWord();
				System.out.println(userName);
				System.out.println(passWord);
				
				
				//ʵ��������֤����
				Message mess=new Message();
			     mess.setSender("Server");
			     mess.setReceiver(userName);
				if(passWord.equals("123456")){//����Ƚ�
				
					//���߿ͻ���������֤ͨ������Ϣ�����Դ���һ��Message��
				     mess.setMessageType(Message.message_LoginSuccess);//"1"Ϊ��֤ͨ��
					
				}else{
					mess.setMessageType(Message.message_LoginFailure);
				}
				ObjectOutputStream oss=new ObjectOutputStream(s.getOutputStream());
				oss.writeObject(mess);
				
				
				//���������������������Ϣ��Ӧ��Ҫ�½�һ�������߳�
				if(passWord.equals("123456")){
					hmSocket.put(userName,s);
					new ServerReceiverThread(s).start();//
				}		
			}
			
	    } catch (IOException e) {//������벻һ����ʹ��
			e.printStackTrace();//�����쳣printStackTrace��ӡ������쳣�ĵ�ַ��Ϣ
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	}