//package database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ServerSideCommunications {

	 public static void main(String[] args) throws IOException
	    {
		 Runtime.getRuntime().addShutdownHook(new Thread() {
		        public void run() {
		            	System.out.println("closing Server");
		            	//Database.Shutdown();
		        }
		    });
	    
	        // server is listening on port 5056
		 ServerSocket ss = new ServerSocket(5056);

	        // running infinite loop for getting
	        // client request
	        while (true)
	        {
	            Socket s = null;
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

	            }catch (Exception e){
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
        //creates new data base object for each thread.
        DB=new DataBase();
    }

    @SuppressWarnings("unchecked")
	@Override
    public void run()
    {
    	ArrayList <String> received = new ArrayList<String>();

        while (true)
        {
            try {
                try {
                				
								received = (ArrayList <String>) dis.readObject();
								
								System.out.print("What we got:");
								System.out.println(received);

								} catch (ClassNotFoundException e) {
									received.add("ClassNotFoundException");
									e.printStackTrace();
								}
								if (received.get(0).equals("Exit")){
									// exit once it received the Exit from input
									System.out.println("Exit");
									
									dis.close();
									dos.close();
									s.close();
									break;
				}
								// sends what it received to data base and send back
								//database result
								dos.writeObject(DB.handles(received));
								//dos.writeObject(received);
								
            } catch (IOException e) {
                e.printStackTrace();
								//connection lost
								// allows thread to exit since we don't want to reconnect
								
								try {
									dis.close();
									dos.close();
									s.close();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								break;
            }
            
        }
    }
}

