import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
public class DataBase
{
    private Connection connection;
    private final ArrayList<String> error = new ArrayList<>();
    //private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private final String user = "";
    private final String pass = "";
    private final String database_loc_uncreated="jdbc:derby";
    private final String database_loc="jdbc:derby:rental_database";
    public DataBase()
    {
        error.add("Nak");
        try
        {
            connection = DriverManager.getConnection(database_loc, user, pass);
        }catch(SQLException e)
        {
            try
            {
                connection = DriverManager.getConnection(database_loc_uncreated, user, pass);
                Statement s = connection.createStatement();
                s.executeUpdate("CREATE DATABASE rental_database;");
                connection.setCatalog("rental_database");
                s.executeUpdate("CREATE TABLE people (name VARCHAR(20), dob DATE, address VARCHAR(75), phone_number VARCHAR(13), id INT AUTOINCREMENT);CREATE TABLE tenants (tenantID INT, billableMember int);CREATE TABLE rentals (id INT AUTOINCREMENT, address VARCHAR(75), description VARCHAR(300), price DOUBLE, type VARCHAR (15));CREATE TABLE datepairs (tenantID INT, rentalID INT, start DATE, end DATE);");
            }catch(SQLException e2)
            {
                e2.printStackTrace();
            }
        }
    }
    public ArrayList<String> handle(ArrayList<String> input)
    {
        try
        {
            switch(input.get(0))
            {
                case "getRentalByID":
                    return getRentalByID(input.get(1));
                case "getRentalByType":
                    return getRentalByType(input.get(1), input.get(2), input.get(3));
                case "setRental":
                    return setRental(input.get(1), input.get(2), input.get(3), input.get(4));
                case "addPerson":
                    return addPerson(input.get(1), input.get(2), input.get(3), input.get(4));
                case "addTenant":
                    return addTenant(input);
                //case "updateTenant":
                //    return updateTenant(input.get(1), input.get(2));
                case "addRental":
                    return addRental(input.get(1), input.get(2), input.get(3), input.get(4));
            }
        }
        catch(SQLException e)
        {
            return error;
        }
        return error;
    }
    private ArrayList<String> getRentalByID(String ID) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM rentals WHERE id = %s", ID));
        ArrayList<String> result = new ArrayList<>();
        for(int i=0; i<resultSet.getMetaData().getColumnCount(); i++)
            result.add((String) resultSet.getObject(i));
        return result;
    }
    private ArrayList<String> getRentalByType(String type, String start, String end) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT id, address FROM rentals WHERE type = %s", type));
        ArrayList<String> result = new ArrayList<>();
        while(resultSet.next())
            for(int i=0; i<resultSet.getMetaData().getColumnCount(); i++)
                result.add((String) resultSet.getObject(i));
        return result;
    }
    private ArrayList<String> setRental(String rentalID, String start, String end, String tenantID) throws SQLException
    {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT reservations FROM rentals WHERE rentalID = %s", rentalID));
        String reservations = (String)resultSet.getObject(0);
        /*Do some stuff here to check if new start and end are conflicting with anything, insert them into list and then resort list*/
        return new ArrayList<String>();
    }
    private ArrayList<String> addPerson(String name, String DOB, String address, String phone_number) throws SQLException
    {
        String s = String.format("INSERT INTO people (name, dob, address, phone_number) values(%s, %s, %s, %s)", name, DOB, address, phone_number);
        PreparedStatement statement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        long id = statement.getGeneratedKeys().getLong(1);
        ArrayList<String> r = new ArrayList<>();
        r.add("addPerson");r.add(Long.toString(id));
        return r;
    }
    private ArrayList<String> addTenant(ArrayList<String> input) throws SQLException
    {
        return new ArrayList<String>();
    }
    private ArrayList<String> addRental(String address, String description, String price, String type) throws SQLException
    {
        connection.createStatement().executeQuery(String.format("INSERT INTO rentals (address, description, price, type) values(%s, %s, %s, %s)", address, description, price, type));
        ArrayList<String> r = new ArrayList<>();
        r.add("addPerson");r.add("Ak");
        return r;
    }
}
