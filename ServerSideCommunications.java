import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ServerSideCommunications {
	
	 @SuppressWarnings("resource")
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
    DataBase DB;
    
    
      
  
    // Constructor 
    public ClientHandler(Socket s, ObjectInputStream dis, ObjectOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis; 
        this.dos = dos; 
        DB=new DataBase();
    } 
  
    @SuppressWarnings("unchecked")
	@Override
    public void run()  
    { 
    	ArrayList <String> received = new ArrayList <String> (); 
       
        while (true)  
        { 
            try { 
                try {
					received = (ArrayList <String>) dis.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
                dos.writeObject(DB.handle(received));
                
                
                
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
    } 
} 
