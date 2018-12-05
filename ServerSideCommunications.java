//package database;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class ServerSideCommunications {
	
	 public static void main(String[] args) throws IOException  
	    { 
	        // server is listening on port 5056 
		 ServerSocket ss = new ServerSocket(5056); 
	          
	        // running infinite loop for getting 
	        // client request 
	        System.out.println("gettign to loop");
	        while (true)  
	        { 
	            Socket s = null; 
	            System.out.println("got into loop");
	              
	            try 
	            { 
	                // socket object to receive incoming client requests 
	                s = ss.accept(); 
	                  
	                System.out.println("A new client is connected : " + s); 
	                  
	                // obtaining input and out streams 
	                ObjectInputStream dis = new ObjectInputStream(s.getInputStream()); 
	                ObjectOutputStream dos = new ObjectOutputStream(s.getOutputStream());
	                dos.flush();
	                  
	                System.out.println("Assigning new thread for this client"); 
	  
	                // create a new thread object 
	                Thread t = new ClientHandler(s, dis, dos); 
	  
	                // Invoking the start() method 
	                t.start(); 
	                  
	            } 
	            catch (Exception e){ 
	                try {
						s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	                e.printStackTrace(); 
	            } 
	        } 
		 
	 }
	       
		 
	  
}


class ClientHandler extends Thread  
{ 
     
    final ObjectInputStream dis; 
    final ObjectOutputStream dos; 
    final Socket s; 
    //Create new DataBase Object for each thread
   // private DataBase DB
      
  
    // Constructor 
    public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        //DB=new DataBase();
    } 
  
    @Override
    public void run()  
    { 
        String[] received = null; 
        String[] toreturn; 
        while (true)  
        { 
            try { 
  
                // send to make sure it is connected 
                //dos.writeObject("Connected"); 
                  
                // receive the answer from client 
                try {
					received = (String[]) dis.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
                System.out.println(received[0]);
                  
                if(received[0].equals("Exit")) 
                {  
                    System.out.println("Client " + this.s + " sends exit..."); 
                    System.out.println("Closing this connection."); 
                    this.s.close(); 
                    System.out.println("Connection closed"); 
                    break; 
                } 
                  
                // to write back to client with results
              //  dos.writeObject(DB.handles(received));
                  
                //To see if input works. not required **
                String[] it_send=new String[1];
                switch (received[0]) { 
                  
                    case "Date" : 
                        System.out.println("date");
                        
                        it_send[0]="bla";
                        dos.writeObject(it_send);
                        break; 
                          
                    case "Time" : 
                        System.out.println("Time");
                        dos.writeObject(it_send);
                        break; 
                          
                    default: 
                        System.out.println("not valid");
                        dos.writeObject(it_send);
                        break; 
                } 
                
                //Not required**
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
          
        try
        { 
            // closing resources 
            this.dis.close(); 
            this.dos.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
} 
