package Experiment1;

public class LinkedList <T>
{
    ListNode<T> first;
    ListNode<T> last;
    private int size=0;
    private class ListNode<T>
    {
        public T data;
        public ListNode<T> link;
        public ListNode(T obj)
        {
            data =obj;
            link=null;
        }
        public ListNode(T obj,ListNode<T> node)
        {
            data=obj;
            link=node;
        }

    }
    public void add(T data)
    {
        insert(size, data);
    }
    public int size()
    {
        return size;
    }
    //在指定索引插入元素
    public void insert(int i,T x)
    {
        ListNode<T> p=first;
        int k=0;
        while(p!=null&&k<i-1)//定位
        {
            p=p.link;
            k++;
        }
        if(i<0||p == null&&i!=0 )//抛出异常
        {
            throw new java.util.NoSuchElementException();
        }
        ListNode<T> newNode=new ListNode<T>(x,null);
        if(i==0)
        {
            newNode.link=first;
            first=newNode;
        }
        else
        {
            newNode.link=p.link;
            p.link=newNode;
        }
        if (newNode.link==null)//在结尾
        {
            last = newNode;
        }
        size++;
    }
    //更改元素
    public void set(int i,T data)
    {
        ListNode<T> p=first;
        int k=0;
        while(p!=null&&k<i)//定位
        {
            p=p.link;
            k++;
        }
        if(i<0||p == null&&i!=0 )//抛出异常
        {
            throw new java.util.NoSuchElementException();
        }
        p.data=data;
    }
    public void removeAll(T data)
    {
        while(search(data)!=-1)
        {
            remove(search(data));
        }
    }
    public LinkedList<T> find_All(T data)
    {
        LinkedList<T> find = new LinkedList<>();
        for(int i=0;i<size;i++)
        {
            if(get(i).equals(data))
            find.add(data);
        }
        if(find.size==0)
            throw new java.util.NoSuchElementException();
        return find;
    }
    //删除指定索引元素
    public void remove(int i)
    {
        ListNode<T> p=first;
        int k=0;
        while(p!=null&&k<i-1)//定位
        {
            p=p.link;
            k++;
        }
        if(i<0||p==null&&p.link==null)//抛出异常
        {
            throw new java.util.NoSuchElementException();
        }
        if(i==0)
        {
            first=first.link;
        }
        else
        {
            p.link=p.link.link;
        }
        if(p.link==last)
        {
            last=p;
        }
        size--;
    }
    //查找指定元素的索引
    public int search(T data)
    {
        ListNode<T> p=first;
        int i=0;
        boolean flag=false;
        for(i=0;p!=null;i++)
        {
            if(p.data.equals(data))
            {
                flag=true;
                break;
            }
            p=p.link;
        }
        if(flag)
        {
            return i;
        }
        else
        {
            return -1;
        }
    }
    public T get(int i)
    {
        ListNode<T> p=first;
        int k=0;
        while(p!=null&&k<i)//定位
        {
            p=p.link;
            k++;
        }
        return p.data;
    }
}
