package Experiment3;

import java.util.HashMap;

public class Container
{
    HashMap<String,Part> nameMap=new HashMap<>();
    public void putin(String name,Part part)
    {
        nameMap.put(name,part);
    }
    public Part find(String name)
    {
        return nameMap.get(name);
    }
}
