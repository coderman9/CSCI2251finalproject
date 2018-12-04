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

public class ClientSideCommunications {
	
	private InetAddress ip;
	private Socket s;
	
	private ObjectOutputStream dos;
	private ObjectInputStream dis;
	
	 public  ClientSideCommunications(InetAddress ad) throws UnknownHostException, IOException  
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
	public String[] send(String[] tosend) throws IOException {
		
           
            dos.writeObject(tosend); 
              
            // If client sends exit,close this connection  
            // and then break from the while loop 
            if(tosend[0].equals("Exit")) 
            { 
            	
                System.out.println("Closing this connection : " + s); 
                s.close(); 
                System.out.println("Connection closed");
                //scn.close(); 
	            dis.close(); 
	            dos.close();
                
            } 
              
            // printing date or time as requested by client
            if(!tosend[0].equals("Exit")) {
            String[] received = null;
			try {
				received = (String[]) dis.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.print(received[0]);
            return received;
            }
			return null;
        } 
	

          
	public static void main (String[] args) {
		byte[] ipAddr = new byte[]{(byte) 192, (byte) 168, 0, 1};
		ClientSideCommunications cl = null;
		Scanner scn= new Scanner(System.in);
		
			InetAddress icup = null;
			try {
				 icup = InetAddress.getByAddress(ipAddr);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				 cl =new ClientSideCommunications(icup);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(true) {
				

                String get = scn.nextLine();
                String[] tosend=new String[1];
                tosend[0]=get;
                try {
					cl.send(tosend);
					System.out.println(tosend[0]);
					if(tosend[0].equals("Exit")) {
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
	 


