import java.util.Date;
public class Person
{
    private String name;
    private Date DOB;
    private int ID;
    public Person(String name, Date DOB, int ID)
    {
        this.name=name;this.DOB=DOB;this.ID=ID;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void setDOB(Date DOB)
    {
        this.DOB=DOB;
    }
    public Date getDOB()
    {
        return DOB;
    }
    public void setID(int ID)
    {
        this.ID=ID;
    }
    public int getID()
    {
        return ID;
    }
    //notice no update like in UML bc we have a billion getters/setters anyway
}
