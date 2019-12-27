package Experiment1;
public class LinkedListTest
{
    public static void main(String args[])
    {
        LinkedList<String> Test=new LinkedList();
        //增：
        Test.add("Hello, ");
        Test.add("I ");
        Test.add("am ");
        Test.add("not ");
        Test.add("not ");
        Test.add("Gabriel.");
        for(int i=0;i<Test.size();i++)
        {
            System.out.print(Test.get(i));
        }
        //查所有:
        System.out.println("\nall the element 'not':");
        LinkedList<String> find=new LinkedList<>();
        find=Test.find_All("not ");
        for(int i=0;i<find.size();i++)
        {
            System.out.print(find.get(i));
        }
        //删：
        System.out.println("\nAfter removing the element 'not':");
        Test.removeAll("not ");
        for(int i=0;i<Test.size();i++)
        {
            System.out.print(Test.get(i));
        }
        System.out.println("\nLength:"+Test.size());
        //查：
        System.out.println("Where is 'Gabriel.':"+Test.search("Gabriel."));
        //改：
        System.out.println("Change 'Gabriel.' to 'John.':");
        Test.set(3,"John.");
        for(int i=0;i<Test.size();i++)
        {
            System.out.print(Test.get(i));
        }
        System.out.println("\n"+Test.size());
    }
}
