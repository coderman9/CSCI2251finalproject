//package database;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class ClientSideCommunications {
	private Socket s;
	private ObjectOutputStream dos;
	private ObjectInputStream dis;

	 public ClientSideCommunications(InetAddress ad) throws UnknownHostException, IOException
	    {
		 //System.out.println("client setup");

	            // establish the connection with server port 5056
				// connect to IP address
	            s = new Socket(ad, 5056);
	            // obtaining input and out streams
	           dos = new ObjectOutputStream(s.getOutputStream());
	           //dos.flush();
	           dis = new ObjectInputStream(s.getInputStream());
	    }
	@SuppressWarnings("unchecked")
	public ArrayList<String> send(ArrayList<String> tosend) throws IOException {
		dos.flush();
		dos.writeObject(tosend);
      
      ArrayList<String> received = new ArrayList<String>();
			try {
				
				received = (ArrayList<String>) dis.readObject();
				
			} catch (ClassNotFoundException e) {
				
				received.add("ClassNotFoundException");
				e.printStackTrace();
			}
			//returns  ClassNotFoundException  if error was encountered
			return received;
      }

			public void closeNetwork(){

				//System.out.println("Closing this connection : " + s);
				ArrayList<String> exit = new ArrayList<String>();
				// send exit in a array list to tell server to disconnect.
				exit.add("Exit");
				try {
					dos.writeObject(exit);
					//dos.flush();
					s.close();
					dis.close();
					dos.close();
					// closes socket,input and output streams.
				} catch (IOException e) {
					e.printStackTrace();
				}
							
			}
			
			
/* 
				public static void main(String[]  args){
		             ClientSideCommunications cl = null;
					Scanner sc = new Scanner(System.in);
					ArrayList<String> sending = new ArrayList<String>();
					ArrayList<String> recived= new ArrayList<String>();
					
					
					InetAddress myHost;
					 try {
				             myHost = InetAddress.getLocalHost();
				             try {
								cl = new ClientSideCommunications(myHost);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            System.out.println(myHost.getHostName());
				        } catch (UnknownHostException ex) {
				            ex.printStackTrace();
				        }
					 while(true) {
						
						 String input = sc.nextLine(); 
						 sending=new ArrayList<String>();
						 sending.add(input);
						
						 if(sending.get(0).equals("Exit")) {
							 cl.closeNetwork();
							 System.out.println("Closing");
							 break;
						 }
						 try {
								recived=cl.send(sending);
							} catch (IOException e) {
								e.printStackTrace();
								break;
							}
						 
						// System.out.println(sending);
						 System.out.println(recived);
			
					 }

				}
			*/

}
