import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.lang.ProcessBuilder;
import java.io.File;
import java.lang.Class;
import java.sql.Date;
public class DataBase
{
    private Connection connection;
    private final ArrayList<String> error = new ArrayList<>();
    private final ArrayList<String> ack = new ArrayList<>();
    private final String driver = "org.apache.derby.jdbc.ClientDriver";
    private final String database_loc_uncreated="jdbc:derby://localhost:1527/rental_database;create=true";
    private final String database_loc="jdbc:derby://localhost:1527/rental_database";
    public DataBase()
    {
        error.add("Nak");
        ack.add("Ak");
        startSQLServer();
        try
        {
            Class.forName(driver).newInstance();    
        }catch(Exception e){e.printStackTrace();}
        try
        {
            connection = DriverManager.getConnection(database_loc);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM people");
            ResultSetMetaData rsmd = rs.getMetaData();
        }catch(SQLException e)
        {
            try
            {
                connection = DriverManager.getConnection(database_loc_uncreated);
                connection.setAutoCommit(false);
                Statement s = connection.createStatement();
                s.addBatch("CREATE TABLE people (name VARCHAR(20), dob DATE, address VARCHAR(75), phone_number VARCHAR(13), id INTEGER GENERATED ALWAYS AS IDENTITY, tenantID INTEGER), billableMember BOOLEAN");
                s.addBatch("CREATE TABLE tenants (tenantID INTEGER GENERATED ALWAYS AS IDENTITY)");
                s.addBatch("CREATE TABLE rentals (id INTEGER GENERATED ALWAYS AS IDENTITY, address VARCHAR(75), description VARCHAR(300), price DOUBLE, type VARCHAR (15), owed DOUBLE)");
                s.addBatch("CREATE TABLE reservations (tenantID INTEGER, rentalID INTEGER, start DATE, end_date DATE)");
                s.executeBatch(); 
                connection.commit();  
            }catch(SQLException e2) 
            {
                e2.printStackTrace();
            }
        }
        try
        {
            connection.close();
        }catch(SQLException e){e.printStackTrace();}
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
                case "getTenant":
                    return getTenant(input.get(1));
                case "addRental":
                    return addRental(input.get(1), input.get(2), input.get(3), input.get(4));
                case "getBilling":
                    return getBilling(input.get(1), input.get(2));
                case "setBilling":
                    return setBilling(input.get(1), input.get(2));
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return error;
        }
        return error;
    }
    public void startSQLServer()
    {
        try
        {
            ProcessBuilder pb = new ProcessBuilder("java", "-jar", "/home/benjamin/derby/lib/derbyrun.jar", "server", "start");
            pb.directory(new File("."));
            Process p = pb.start();
            Thread.sleep(4000);
        }catch(Exception e){e.printStackTrace();}
    }
    private ArrayList<String> getRentalByID(String ID) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        //!!! need to add reserved dates
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT id, address, description, price, type FROM rentals WHERE id = %s", ID));
        ArrayList<String> result = new ArrayList<>();
        result.add("getRentalByID");
        resultSet.next();
        for(int i=1; i<=resultSet.getMetaData().getColumnCount(); i++)
        {
            result.add(resultSet.getObject(i).toString());
        }
        statement=connection.createStatement();
        ResultSet rs = statement.executeQuery(String.format("SELECT start, end_date FROM reservations WHERE rentalID = %s", ID));
        for(int i=1; rs.next()&&i<=rs.getMetaData().getColumnCount(); i++)
        {
            result.add(rs.getObject(i).toString());
        }
        connection.close();
        return result;
    }
    private ArrayList<String> getRentalByType(String type, String start, String end_date) throws SQLException
    {
        //!!! do date stuff
        connection = DriverManager.getConnection(database_loc);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT id, address FROM rentals WHERE type = '%s'", type));
        ArrayList<String> result = new ArrayList<>();
        result.add("getRentalByType");
        while(resultSet.next())
            result.add(resultSet.getObject(1).toString()+","+resultSet.getObject(2).toString());
        connection.close();
        return result;
    }
    private ArrayList<String> setRental(String rentalID, String start, String end, String tenantID) throws SQLException
    {        
        connection = DriverManager.getConnection(database_loc);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM reservations RIGHT JOIN rentals ON reservations.rentalID = rentals.id WHERE id = %s AND (CAST('%s' AS DATE)>reservations.end_date OR CAST('%s' AS DATE)<reservations.start OR reservations.start is null)", rentalID, start, end));
        if(!resultSet.next())
            return error;
        ArrayList<String> r = new ArrayList<>();
        statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO reservations VALUES(%s, %s, CAST('%s' as DATE), CAST('%s' as DATE))", tenantID, rentalID, start, end));
        connection.close();
        return ack;
    }
    private ArrayList<String> addPerson(String name, String DOB, String address, String phone_number) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        String s = String.format("INSERT INTO people (name, dob, address, phone_number) values('%s', '%s', '%s', '%s')", name, DOB, address, phone_number);
        PreparedStatement statement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet key = statement.getGeneratedKeys();
        key.next();
        long id = key.getLong(1);
        ArrayList<String> r = new ArrayList<>();
        r.add("addPerson");r.add(Long.toString(id));
        connection.close();
        return r;
    }
    private ArrayList<String> addTenant(ArrayList<String> input) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        String s = String.format("INSERT INTO tenants VALUES (DEFAULT)");
        PreparedStatement statement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet key = statement.getGeneratedKeys();
        key.next();
        long id = key.getLong(1);
        s = String.format("UPDATE people SET tenantID = %s WHERE id IN (%s)", id, arrayListToString(input, 2));
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(s);
        stmt.executeUpdate(String.format("UPDATE people SET billableMember = TRUE WHERE id = %s", input.get(1)));
        connection.close();
        return ack;
    }
    private String arrayListToString(ArrayList<String> input, int startIndex)
    {
        String r = "";
        for(int i = startIndex; i<input.size();i++)
            r+=input.get(i)+",";
        return r.substring(0, r.length()-1);
    }
    private ArrayList<String> addRental(String address, String description, String price, String type) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        connection.createStatement().executeUpdate(String.format("INSERT INTO rentals (address, description, price, type) values('%s', '%s', %f, '%s')", address, description, Double.parseDouble(price), type));
        connection.close();
        return ack;
    }
    private ArrayList<String> getTenant(String name) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM people WHERE name = '%s' AND billableMember = TRUE", name));
        ArrayList<String> result = new ArrayList<>();
        result.add("getTenant");
        while(resultSet.next())
            for(int i=1; i<=resultSet.getMetaData().getColumnCount(); i++)
                result.add(resultSet.getObject(i).toString());
        connection.close();
        return result;
    }
    private ArrayList<String> getBilling(String name, String date) throws SQLException
    {
        connection = DriverManager.getConnection(database_loc);
        //have to return a list, where each element is the rentalID and amountowed for a certain rental, based on who is there at specified date
        //Statement stmt = connection.createStatement();
        //String s = String.format("SELECT 
        //connection.close();
        return new ArrayList<String>();
    }
    private ArrayList<String> setBilling(String rentalID, String amount) throws SQLException
    {
        //update the rental specified by rentalID by amount
        connection = DriverManager.getConnection(database_loc);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT owed FROM rentals WHERE rentalID = %s", rentalID));
        long owed = resultSet.getLong(1);
        owed+=Long.parseLong(amount);
        String s = String.format("UPDATE rentals SET owed = %s WHERE rentalID = %s", owed, rentalID);
        statement = connection.createStatement();
        statement.executeUpdate(s);
        ArrayList<String> r = new ArrayList<>();
        r.add(Long.toString(owed));
        connection.close();
        return r;
    }
}
