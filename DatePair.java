/*
Ben Anker and AJ Fenton in collaboration with Cesar Ornelas
CSCI 2251 Final Project
Used to represent start and end dates
*/

import java.util.Date;
public class DatePair extends Tuple<Date>
{
    public final int ID;
    public DatePair(Date start, Date end, int tenantID)
    {
        super(start, end);
        this.ID=ID;
    }
}
