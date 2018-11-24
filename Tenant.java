/*
Ben Anker in collaboration with AJ Fenton and Cesar Ornelas
CSCI 2251 Final Project
Used to represent the tenant (which is a group of people)
*/
import java.util.HashMap;
public class Tenant
{
    //made this a hashmap to support easy look ups by ID
    private HashMap<int, Person> tenants=new HashMap<>();
    private int billableMember;
    private String billAddress;
    //I have reservations about representing the statement as a string in the tenant class but I'm 
    //trying not to make too many changes to the design yet
    private String statement;
    public Tenant(Person[] people)
    {
        for(Person p : people)
            this.tenants.put(p.getID, p);
    }
    public void addPerson(Person p)
    {
        this.tenants.put(p.getID, p);
    }
    public void removePerson(int ID)
    {
        this.tenants.remove(ID);
    }
    public int getBillableMember()
    {
        return this.tenants.get(ID);
    }
    public void setBillableMember(int ID)
    {
        this.billableMember=ID;
    }
    public HashMap<int, Person> getPeople()
    {
        return this.tenants;
    }
    public void setStatement(String statement)
    {
        this.statement=statement;
    }
    public String getStatement(String statement)
    {
        return statement;  
    }
    public void setBillAddress(String address)
    {
        this.billAddress=address;
    }
    public String getBillAddress()
    {
        return billAddress;
    }
}
