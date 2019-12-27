package Experiment3;

import java.util.HashMap;

public class Part
{
    String name;
    HashMap<Part,Integer> subPart=new HashMap<>();
    public Part(String name)
    {
        this.name=name;
    }
    public String isWhat()
    {
        String to_string="Part "+this.name+" subparts are:\n";
        if(subPart.size()!=0)
        {
            for(Part p:subPart.keySet())
            {
                to_string=to_string+"\t"+subPart.get(p)+" "+p.name+"\n";
            }
        }
        if(subPart.size()==0)
        {
            to_string=to_string+"\tIt has no subparts\n";
        }
        return to_string;
    }
}
