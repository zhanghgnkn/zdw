package com.yychatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


    public class FriendList extends JFrame implements ActionListener,MouseListener{
	CardLayout cardLayout;
	
	JPanel friendpanel;
	JButton friendbutton;
	JPanel strangeanddislikepanel;
	JButton strangebutton;
	JButton dislikebutton;
	
	JScrollPane friendscrollpane;
	JPanel friendlistpanel;
	static final int FRIENDCOUNT=51;
	JLabel[] friendlabel=new JLabel[FRIENDCOUNT];
	
	
	JPanel strangepanel;
	JPanel friendandstrangepanel;
	JButton friendbutton1;
	JButton strangebutton1;
	JButton dislikebutton1;
	
	JScrollPane strangescrollpane;
	JPanel strangelistpanel;
	static final int STRANGERCOUNT=21;
	JLabel[] strangelabel=new JLabel[STRANGERCOUNT];
	
	JPanel dislikepanel;
	JPanel friendandstrangeanddislike;
	JButton friendbutton2;
	JButton strangebutton2;
	JButton dislikebutton2;
	
	JScrollPane dislikescrollpane;
	JPanel dislikelistpanel;
	static final int DISLIKECOUNT=21;
	JLabel[] dislikelabel=new JLabel[DISLIKECOUNT];
     
	String userName;
  
	public FriendList(String userName){
		this.userName=userName;//局部变量给成员变量赋值 
		//卡1
		friendpanel=new JPanel(new BorderLayout());
		friendbutton=new JButton("我的好友");
		friendpanel.add(friendbutton,"North");
		
		
		friendlistpanel=new JPanel(new GridLayout(FRIENDCOUNT-1,1));
		for(int i=1;i<FRIENDCOUNT;i++){
			friendlabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			friendlabel[i].addMouseListener(this);
			friendlistpanel.add(friendlabel[i]);
		}
		friendscrollpane=new JScrollPane(friendlistpanel);
		friendpanel.add(friendscrollpane);
		
		
		strangeanddislikepanel=new JPanel(new GridLayout(2,1));
		strangebutton=new JButton("陌生人");
		strangebutton.addActionListener(this);
		
		dislikebutton=new JButton("黑名单");
		strangeanddislikepanel.add(strangebutton);
		strangeanddislikepanel.add(dislikebutton);
		friendpanel.add(strangeanddislikepanel,"South");
		
		
		//卡2
		strangepanel=new JPanel(new BorderLayout());
		friendandstrangepanel=new JPanel(new GridLayout(2,1));
		friendbutton1=new JButton("我的好友");
		friendbutton1.addActionListener(this);
		
		
		strangebutton1=new JButton("陌生人");
		friendandstrangepanel.add(friendbutton1);
		friendandstrangepanel.add(strangebutton1);
		strangepanel.add(friendandstrangepanel,"North");
		
		strangelistpanel=new JPanel(new GridLayout(STRANGERCOUNT-1,1));
		for(int i=1;i<STRANGERCOUNT;i++){
			strangelabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			strangelabel[i].addMouseListener(this);//添加鼠标监听
			strangelistpanel.add(strangelabel[i]);
		}
		strangescrollpane=new JScrollPane(strangelistpanel);
		strangepanel.add(strangescrollpane);
		
		
		dislikebutton1=new JButton("黑名单");
		dislikebutton1.addActionListener(this);
		strangepanel.add(dislikebutton1,"South");
		
		
		
		//卡3
		dislikepanel=new JPanel(new BorderLayout());
		friendandstrangeanddislike=new JPanel(new GridLayout(3,1));
		friendbutton2=new JButton("我的好友");
		friendbutton2.addActionListener(this);
		strangebutton2=new JButton("陌生人");
		strangebutton2.addActionListener(this);
		dislikebutton2=new JButton("黑名单");
		friendandstrangeanddislike.add(friendbutton2);
		friendandstrangeanddislike.add(strangebutton2);
		friendandstrangeanddislike.add(dislikebutton2);
		dislikepanel.add(friendandstrangeanddislike,"North");
		
		dislikelistpanel=new JPanel(new GridLayout(DISLIKECOUNT-1,1));
		for(int i=1;i<DISLIKECOUNT;i++){
			dislikelabel[i]=new JLabel(i+"",new ImageIcon("images/YY1.gif"),JLabel.LEFT);
			dislikelabel[i].addMouseListener(this);
			dislikelistpanel.add(dislikelabel[i]);
		}
		dislikescrollpane=new JScrollPane(dislikelistpanel);
		dislikepanel.add(dislikescrollpane);
		
		
		
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(friendpanel,"1");
		this.add(strangepanel,"2");
		this.add(dislikepanel,"3");
		
		this.setSize(250,500);
		this.setTitle(this.userName+"的好友列表");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		//FriendList friendList=new FriendList();
    }
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource()==strangebutton){
			cardLayout.show(this.getContentPane(),"2");
	    }
		if(arg0.getSource()==friendbutton1){
			cardLayout.show(this.getContentPane(),"1");
	    }
		
		if(arg0.getSource()==dislikebutton1){
			cardLayout.show(this.getContentPane(),"3");
	    }
		if(arg0.getSource()==friendbutton2){
			cardLayout.show(this.getContentPane(),"1");
	    }
		if(arg0.getSource()==strangebutton2){
			cardLayout.show(this.getContentPane(),"2");
	    }
		
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount()==1){
			JLabel jlbl=(JLabel)arg0.getSource();
			String receiver=jlbl.getText();
			//new FriendChat(this.userName,receiver);
			new Thread(new FriendChat(this.userName,receiver)).start();
		}
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jlabel=(JLabel)arg0.getSource();
		jlabel.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jlabel=(JLabel)arg0.getSource();
		jlabel.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
