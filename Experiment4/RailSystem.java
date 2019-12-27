package Experiment4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class RailSystem {
    private SeqList city_list = new SeqList();
    HashMap map = new HashMap();
    int number_of_service=0;
    public int[] prenode;
    int distance=0;

    public SeqList getCity_list() {
        return city_list;
    }

    public int getFirstNeighbor(int v) {
        if (v >= 0 && v <= city_list.size()) {
            Service s = city_list.get(v).getAdj_list();
            if (s != null) {
                return s.getDest();
            }
        }
        return -1;
    }

    public int getNextNeighbor(int v1, int v2) {
        if (v1 >= 0 && v1 < city_list.size()) {
            Service s = city_list.get(v1).getAdj_list();
            while (s != null) {
                if ((s.getDest() == v2) && (s.getLink() != null)) {
                    return s.getLink().getDest();
                } else s = s.getLink();
            }
        }
        return -1;
    }

    public void insertCity(City city) {
        city_list.add(city);
    }

    public void insertService(int v1, int v2, int cost, int distance) {
        Service s1 = city_list.get(v1).getAdj_list();
        if (s1 != null) {
            while (s1.getLink() != null) {
                s1 = s1.getLink();
            }
            s1.setLink(new Service(v2, cost, distance, null));
//            System.out.println(v1+" "+v2+" "+distance);
        }
        if (s1 == null) {
            city_list.get(v1).setAdj_list(new Service(v2, cost, distance, null));
//            System.out.println(v1+" "+v2+" "+distance);
        }
        number_of_service++;
    }

    public int service_cost(int v1, int v2) {
        Service s1 = city_list.get(v1).getAdj_list();
        while (s1 != null) {
            if (s1.getDest() == v2) {
                return s1.getCost();
            } else {
                s1 = s1.getLink();
            }
        }
        return -1;
    }
    public int service_distance(int v1, int v2) {
        Service s1 = city_list.get(v1).getAdj_list();
        while (s1 != null) {
            if (s1.getDest() == v2) {
//                System.out.println(v1+" "+v2+" "+s1.getDistance());
                return s1.getDistance();
            } else {
                s1 = s1.getLink();
            }
        }
        return 0;
    }
    public HashMap getMap() {
        return map;
    }

    public void load_service(String path) {
        try {
            String str;
            File infile = new File(path);
            FileInputStream fis = new FileInputStream(infile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while ((str = br.readLine()) != null) {
                String[] strList = str.split(" ");
                City city = new City(strList[0]);
                if (this.getCity_list().where(city) == -1) {
                    this.insertCity(city);
                    map.put(strList[0], this.getCity_list().where(city));
                }
            }
            fis = new FileInputStream(infile);
            br = new BufferedReader(new InputStreamReader(fis));
            while ((str = br.readLine()) != null) {
                String[] strList = str.split(" ");
                int v1 = (int) map.get(strList[0]);
                int v2 = (int) map.get(strList[1]);
                int cost = Integer.parseInt(strList[2]);
                int distance = Integer.parseInt(strList[3]);
                this.insertService(v1, v2, cost, distance);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[] Cheapest(int v) {
        if (v < 0 || v >= city_list.size())
            throw new ArrayIndexOutOfBoundsException();
        boolean[] st = new boolean[city_list.size()];// 默认初始为false
        int[] cost = new int[city_list.size()];// 存放源点到其他点的距离
        int[] preNode = new int[city_list.size()];
        for (int i = 0; i < city_list.size(); i++) {
            cost[i] = Integer.MAX_VALUE;
            preNode[i]=-1;
        }
        City current;
        current = city_list.get(getFirstNeighbor(v));
        while (current != null) {
            cost[city_list.where(current)] = service_cost(v, city_list.where(current));
//            System.out.println(city_list.where(current)+" cost:"+cost[city_list.where(current)]);
            current = city_list.get(getNextNeighbor(v, city_list.where(current)));
        }
        cost[v] = 0;
        st[v] = true;
        // 处理从源点到其余顶点的最短路径
        for (int i = 0; i < city_list.size(); i++) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            int j;
            // 比较从源点到其余顶点的路径长度
            for (j = 0; j < city_list.size(); j++) {
                // 从源点到j顶点的最短路径还没有找到
                if (st[j] == false) {
                    // 从源点到j顶点的路径长度最小
                    if (cost[j] <= min)
                    {
                        index = j;
                        min = cost[j];
                    }
                }
            }
            if(i==0){
                preNode[index]=v;
//                System.out.println(index+" preNode "+v);
            }
            // 找到源点到索引为index顶点的最短路径长度
            if (index != -1) {
                st[index] = true;
            }
            // 更新当前最短路径及距离
            for (int w = 0; w < city_list.size(); w++) {
                if (st[w] == false) {
                    current = city_list.get(getFirstNeighbor(w));
                    while (current != null) {
                        if (city_list.where(current) == index)
                            if((min + service_cost(city_list.where(current),w)) < cost[w])
                            {
                                cost[w] = min + service_cost(city_list.where(current),w);
                                preNode[w]=index;
                                break;
                            }
                        current = city_list.get(getNextNeighbor(w, city_list.where(current)));
                    }
                }
            }
        }
        prenode=preNode;
        return cost;
    }
    public String get_path(int v1,int v2)
    {
        String path_to_string="";
        Stack<Integer> path=new Stack<>();
        int node=v2;
        int node2;
        path.push(node);
        while (node!=v1)
        {
            node2=node;
            node=prenode[node];
            if(node==-1)
            {
                distance=distance+Integer.MAX_VALUE;
                return "No path";
            }
            else
            {
                path.push(node);
                distance=distance+service_distance(node,node2);
            }
        }
        for(int i=0;!path.isEmpty();i++)
        {
            node=path.pop();
            if(i==0)
                path_to_string=path_to_string+city_list.get(node);
            else
                path_to_string=path_to_string+" to "+city_list.get(node);
        }
        return path_to_string;
    }
    public int total_distance()
    {
        return distance;
    }
}
