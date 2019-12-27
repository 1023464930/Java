package Experiment2;

import java.io.*;
import java.text.DecimalFormat;

public class Fifo extends Simulator
{
    String str;
    FileInputStream fis;
    BufferedReader br;
    FileOutputStream fos;
    BufferedWriter bw;
    Boolean isLoad=true;
    Boolean isFree=true;
    int allTime=1;
    Event e;
    Event e2=new Event();
    int jobs=0;
    int Aggregate=0;
    double mean=0;
    Queue<Event> finish=new Queue<>();
    DecimalFormat df=new DecimalFormat("0.000");
    @Override
    public void simulator()
    {
        try
        {
            bw.write("FIFO Simulation \n\n");
            loadWorkload();
            allTime=e.getArrival_time();
            while (true)
            {
                if(isLoad)
                {
                    jobs++;
                    bw.write(e.arriving()+"\n");
                }
                if(isFree)
                {
                    e2=workload.getFront();
                    if(e2==null)
                        break;
                    e2.setFinish_time(allTime);
                    finish.enQueue(e2);
                    bw.write(e2.servicing()+allTime+" seconds\n");
                    isFree=false;
                }
                if(allTime==e2.getFinish_time()+seconds_per_page*e2.getJ().getNumber_of_pages()-1)
                {
                    isFree=true;
                    workload.deQueue();
                }
                allTime++;
                loadWorkload();
            }
            bw.write("\n      Total jobs:"+jobs);
            Event e3;
            while ((e3=finish.deQueue())!=null)
            {
                Aggregate=e3.getFinish_time()-e3.getArrival_time()+Aggregate;
            }
            bw.write("\n      Aggregate latency: "+Aggregate+" seconds");
            mean=(double)Aggregate/(double)jobs;
            bw.write("\n      Mean latency: "+df.format(mean)+" seconds");
            br.close();
            bw.close();
            fis.close();
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void loadWorkload()
    {
        try
        {
            if((str=br.readLine())!=null)
            {
                String[] strList = str.split(" ");
                Job j = new Job(strList[2], Integer.parseInt(strList[1]));
                e = new Event(j, Integer.parseInt(strList[0]));
                workload.enQueue(e);
            }
            else
            {
                isLoad=false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadFile(String inFile,String outFile)
    {
        try
        {
            File infile=new File(inFile);
            fis = new FileInputStream(infile);
            br = new BufferedReader(new InputStreamReader(fis));
            File outfile=new File(outFile);
            fos=new FileOutputStream(outfile);
            bw=new BufferedWriter(new OutputStreamWriter(fos));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
