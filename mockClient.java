import java.util.Scanner;
import java.util.ArrayList;
public class mockClient
{
    public static void main(String args[])
    {
        boolean echo = false;
        if(args.length>0)
            echo = true;
        Scanner s = new Scanner(System.in);
        DataBase d = new DataBase();
        while(true)
        {
            String method = System.console().readLine();
            ArrayList<String> params = new ArrayList<>();
            params.add(method);
            String input = System.console().readLine();
            while(!input.equals(""))
            {
                if(echo)
                {
                    System.out.println(input);
                }
                params.add(input);
                input=System.console().readLine();
            }
            ArrayList<String> response = d.handle(params);
            for(String i : response)
                System.out.println(i);
            System.out.println();System.out.println();
        }
    }
}
