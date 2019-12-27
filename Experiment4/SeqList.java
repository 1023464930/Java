package Experiment4;

class SeqList{
    private static final int DEFAULT_CAPACITY = 10;
    private City[] elements;
    private int size;
    public SeqList() {
        elements = new City[DEFAULT_CAPACITY];
        size = 0;
    }

    public SeqList(City obj, int sz) {
        elements = new City[sz];
        size = 0;
    }

    //末尾增加元素：
    public void add(City obj) {
        add(size, obj);
    }

    //指定索引添加元素：
    public void add(int index, City x) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if (elements.length == size)
            ensureCapacity(size * 2 + 1);
        for (int i = size; i > index; i--)
            elements[i] = elements[i - 1];
        elements[index] = x;
        size++;
    }

    //修改元素：set(索引，新值)
    public City set(int index, City newVal) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        City old = elements[index];
        elements[index] = newVal;
        return old;
    }

    //删除元素：
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i <= size - 2; i++) {
            elements[index] = elements[index + 1];
        }
        size--;
        ensureCapacity(size);
    }

    //清空数组：
    public void clare() {
        size = 0;
    }

    //查询数组长度：
    public int size() {
        return size;
    }

    //查询数组是否为空：
    public Boolean isEmpty() {
        return size == 0;
    }

    //查询指定索引的元素：
    public City get(int index) {
        if (index < 0 || index >= size)
        {
//            System.out.println("Wrong city getting index:"+index);
            return null;
        }
        return elements[index];
    }

    //查找元素是否存在：
    public boolean exist(City search) {
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (search.equals(elements[i])) {
                flag = true;
            }
        }
        return flag;
    }
    //查询第一个指定元素索引：
    public int where(City search) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (search.equals(elements[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    //确定容量：
    public void ensureCapacity(int newSize) {
        if (newSize < size)
            return;
        City[] old = elements;
        elements = new City[newSize];
        for (int i = 0; i < size; i++) {
            elements[i] = old[i];
        }
        return;
    }
}