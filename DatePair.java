import java.util.Date;
public class DatePair extends Tuple<Date>
{
    public final int ID;
    public DatePair(Date start, Date end, int ID)
    {
        super(start, end);
        this.ID=ID;
    }
}
