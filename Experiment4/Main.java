package Experiment4;

import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        RailSystem Europe=new RailSystem();
        try
        {
            Scanner scan=new Scanner(System.in);
            Europe.load_service("C:\\Users\\Gabriel\\IdeaProjects\\MyJava\\src\\Experiment4\\services.txt");
            while (true)
            {
                System.out.println("Enter a start and destination city:<'quit' to exit>");
                String start = scan.next();
                if(start.equals("quit"))
                    break;
                String destination = scan.next();
                HashMap map = Europe.getMap();
                Object key1 = start;
                Object key2 = destination;
                int v1 = (int) map.get(key1);
                int v2 = (int) map.get(key2);
//              System.out.println("start and destination index:"+v1+" "+v2);
                int[] cost = Europe.Cheapest(v1);
                Europe.get_path(v1, v2);
                System.out.println("The cheapest route from " + Europe.getCity_list().get(v1) + " to " + Europe.getCity_list().get(v2) + " costs " + cost[v2] + " euros and spans " + Europe.total_distance() + " kilometers");
                System.out.println(Europe.get_path(v1, v2)+"\n\n");
//            打印preNode表：
//            int[] preNode=Europe.prenode;
//            for(int i=0;i<preNode.length;i++)
//            {
//                System.out.println(i+":"+preNode[i]);
//            }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
