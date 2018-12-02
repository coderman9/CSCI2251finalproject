//package database;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Client {
	
	private InetAddress ip;
	private Socket s;
	
	private ObjectOutputStream dos;
	private ObjectInputStream dis;
	
	 public  Client(InetAddress ad) throws UnknownHostException, IOException  
	    { 
		 System.out.println("client setup");
	        
	           
	              
	            // getting localhost ip 
	             //ip = InetAddress.getByName("localhost"); 
	      
	            // establish the connection with server port 5056 
	            s = new Socket(InetAddress.getLocalHost(), 5056);
	            	      
	            // obtaining input and out streams 
	          
	          
	           
	           dos = new ObjectOutputStream(s.getOutputStream());
	           dos.flush();
	           //dos.writeObject("hello");
	           dis = new ObjectInputStream(s.getInputStream()); 
	      
	       
	            
	    } 
	public void send(String tosend) throws IOException {
		
           
            dos.writeObject(tosend); 
              
            // If client sends exit,close this connection  
            // and then break from the while loop 
            if(tosend.equals("Exit")) 
            { 
                System.out.println("Closing this connection : " + s); 
                s.close(); 
                System.out.println("Connection closed");
                //scn.close(); 
	            dis.close(); 
	            dos.close();
                
            } 
              
            // printing date or time as requested by client
            if(!tosend.equals("Exit")) {
            String received = null;
			try {
				received = (String) dis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
            System.out.println(received);
            }
        } 
	
	public String recieve() throws IOException {
		
			try {
				return (String) dis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return("Failed");
			}
		
	}
          
	public static void main (String[] args) {
		byte[] ipAddr = new byte[]{(byte) 192, (byte) 168, 0, 1};
		Client cl = null;
		Scanner scn= new Scanner(System.in);
		
			InetAddress icup = null;
			try {
				 icup = InetAddress.getByAddress(ipAddr);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				 cl =new Client(icup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true) {
				

                try {
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
                String tosend = scn.nextLine();
                try {
					cl.send(tosend);
					if(tosend.equals("Exit")) {
						System.out.println("closing");
						scn.close();
						break;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}		
			}
				
		
		
	}
         
   
		 
}
	 


