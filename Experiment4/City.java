package Experiment4;

public class City
{
    String name;
    private Service adj_list;
    public City(String name) {
        this.name = name;
        this.adj_list=null;
    }
    public String getName() {
        return name;
    }

    public Service getAdj_list() {
        return adj_list;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setAdj_list(Service adj_list) {
        this.adj_list = adj_list;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}

