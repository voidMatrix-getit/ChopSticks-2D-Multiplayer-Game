package sahil.chopsticks;

import java.io.*;
import java.net.*;
import java.util.*;


public class ChopSticksGameServer {

	
	
//	public static DataInputStream dis1=null;
//	public static DataOutputStream dos1=null;
	public static ObjectInputStream ois1=null;
	public static ObjectOutputStream oos1=null;

//	public static DataInputStream dis2=null;
//	public static DataOutputStream dos2=null;
	public static ObjectInputStream ois2=null;
	public static ObjectOutputStream oos2=null;
	
	public static PrintWriter pr=null;
	public static InputStreamReader isr=null;
	
	public static Random r = new Random();

	
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
            
			ServerSocket serverSocket = new ServerSocket(2002);
            System.out.println("Server started. Waiting for players...");

            Socket player1 = serverSocket.accept();
            System.out.println("Player1 connected: " + player1);
            //PrintWriter out;
            
            oos1 = new ObjectOutputStream(player1.getOutputStream());
            oos1.writeUTF("Player1(you) joined this game session first now wait for Player2");
            oos1.flush();
            
            
            Socket player2 = serverSocket.accept();
            System.out.println("Player2 connected: " + player2);
            
            oos2 = new ObjectOutputStream(player2.getOutputStream());
            oos2.writeUTF("Player2(You) joined a game with Player1");
            oos2.flush();
            
            oos1.writeUTF("Game is starting now");
            oos2.writeUTF("Game is starting now");
            
            oos1.flush();
            oos2.flush();
            
//            dis1 = new DataInputStream(player1.getInputStream());
//            dis2 = new DataInputStream(player2.getInputStream());
            
 
            ois1 = new ObjectInputStream(player1.getInputStream());
            ois2 = new ObjectInputStream(player2.getInputStream());
            
            
            new Thread(() -> {
            	
            	System.out.println("inside thread");
            	
            	Vector<Integer> plh1 = new Vector<>(5);
                Vector<Integer> prh1 = new Vector<>(5);
                Vector<Integer> plh2 = new Vector<>(5);
                Vector<Integer> prh2 = new Vector<>(5);
                
                plh1.add(1);
                prh1.add(1);
                plh2.add(1);
                prh2.add(1);
            	
            	try {
            		
            		boolean turn,turn1,turn2;
            		
            		if(r.nextInt(2)==0) {
            			turn=true; //player1
            		}
            		else {
            			turn=false;//player2
            		}
            		
            		while(true) {
                     			
            			System.out.println("inside while");
            			
            			if(turn) {
            				oos1.writeObject(plh1);
                			oos1.flush();
                			oos1.writeObject(prh1);
                			oos1.flush();
                			oos1.writeObject(plh2);
                			oos1.flush();
                			oos1.writeObject(prh2);
                			oos1.flush();
            				oos1.writeBoolean(turn);
            				oos1.flush();
            				turn = ois1.readBoolean();
            				plh1 = (Vector<Integer>) ois1.readObject();
                			prh1 = (Vector<Integer>) ois1.readObject();
                			plh2 = (Vector<Integer>) ois1.readObject();
                			prh2 = (Vector<Integer>) ois1.readObject();
            				
            			}else {
            				oos2.writeObject(plh1);
                			oos2.flush();
                			oos2.writeObject(prh1);
                			oos2.flush();
                			oos2.writeObject(plh2);
                			oos2.flush();
                			oos2.writeObject(prh2);
                			oos2.flush();
            				oos2.writeBoolean(turn);
            				oos2.flush();
            				turn = ois2.readBoolean();
            				plh1 = (Vector<Integer>) ois2.readObject();
                			prh1 = (Vector<Integer>) ois2.readObject();
                			plh2 = (Vector<Integer>) ois2.readObject();
                			prh2 = (Vector<Integer>) ois2.readObject();
            			}
            			
            		}
            		
            		
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            	
            }).start();
            
//            new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					
//					while(true) {
//						try {
//							dos2.writeUTF(dis1.readUTF());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
//			}).start();
//            
//            new Thread(new Runnable() {
//				
//				@Override
//				public void run() {
//					
//					while(true) {
//						try {
//							dos1.writeUTF(dis2.readUTF());
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					
//				}
//			}).start();
//
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
            
          
    }

}

