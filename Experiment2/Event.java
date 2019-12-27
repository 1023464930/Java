package Experiment2;
public class Event
{
    private Job j;
    private int arrival_time;
    private int finish_time;
    public Event()
    {
        arrival_time=0;
        j=null;
        finish_time=0;
    }
    public Event(Job j,int arrival_time)
    {
        this.arrival_time=arrival_time;
        this.j=j;
    }

    public int getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(int arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String arriving()
    {
        return "      Arriving: "+j.getNumber_of_pages()+" pages from "+j.getUser()+" at "+arrival_time+" seconds";
    }
    public String servicing()
    {
        return "      Servicing: "+j.getNumber_of_pages()+" pages from "+j.getUser()+" at ";
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public Job getJ() {
        return j;
    }

    public void setJ(Job j) {
        this.j = j;
    }
}
