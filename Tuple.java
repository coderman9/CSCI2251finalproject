/*
Ben Anker in collaboration with AJ Fenton and Cesar Ornelas
CSCI 2251 Final Project
Used to represent start and end dates
*/

//note that start and end are final since a tuple by definition is immutable
public class Tuple<T>
{
    public final T start;
    public T end;
    public Tuple(T start, T end)
    {
        this.start=start;this.end=end;
    }
}
