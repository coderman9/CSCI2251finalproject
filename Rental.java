/*
Ben Anker in collaboration with AJ Fenton and Cesar Ornelas
CSCI 2251 Final Project
Superclass of the other types of rentals
*/
public class Rental
{
    private String address;
    private String rentalID;
    private int type;
    private Tuple[] reservation;
    private float fees;
    
    public void setAddress(String address)
    {
        this.address=address;
    }
    public String getAddress()
    {
        return address;
    }
    public void setRentalID(String id)
    {
        this.rentalID=id;
    }
    public String getRentalID()
    {
        return rentalID;
    }
    public void setType(int type)
    {
        this.type=type;
    }
    public int getType()
    {
        return type;
    }
    //returns all reservations the attempted add overlaps with
    public Tuple[] addReservation(Tuple res)
    {
        //filler. will complete later
        return new Tuple[0];
    }
}    
    
