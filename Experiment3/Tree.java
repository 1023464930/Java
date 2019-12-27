package Experiment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Tree
{
    Container ct;
    public Tree(String path)
    {
        try
        {
            String inFile=path;
            File infile=new File(inFile);
            FileInputStream fis = new FileInputStream(infile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String str;
            ct=new Container();
            while((str=br.readLine())!=null)
            {
                String[] strList=str.split(" ");
                if(ct.find(strList[0])==null)
                {
                    Part part = new Part(strList[0]);
                    Part part2 = new Part(strList[2]);
                    part.subPart.put(part2, Integer.parseInt(strList[1]));
//                    System.out.println(strList[0]+" "+strList[2]);
                    ct.putin(strList[0],part);
                    ct.putin(strList[2],part2);
                }
                else
                {
                    Part part=ct.find(strList[0]);
                    Part part2=new Part(strList[2]);
                    ct.putin(strList[2],part2);
                    part.subPart.put(part2, Integer.parseInt(strList[1]));
                }
            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void load_queries(String path)
    {
        try
        {
            String inFile=path;
            File infile=new File(inFile);
            FileInputStream fis = new FileInputStream(infile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String str;
            while((str=br.readLine())!=null)
            {
                String[] strList=str.split(" ");
                if(strList[0].equals("whatis"))
                {
                    Part part=ct.find(strList[1]);
//                    System.out.println(strList[1]);
                    System.out.println(part.isWhat());
                }
                if(strList[0].equals("howmany"))
                {
                    Part part=ct.find(strList[2]);
                    System.out.println(strList[2]+" has "+howmany(part,strList[1])+" "+strList[1]+"s");
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public int howmany(Part part,String name)
    {
        if(part.name.equals(name))
        {
            return 1;
        }
        if(part.subPart.isEmpty())
        {
            return 0;
        }
        else
        {
            int sum=0;
            for (Part p : part.subPart.keySet())
                {
                    sum = part.subPart.get(p);
                    sum = sum * howmany(p,name);
                    if(sum!=0)
                    {
                        break;
                    }
                }
            return sum;
        }
    }
}
