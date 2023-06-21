package exe;
import java.util.Scanner;

public class Clock3 {
    Node head;
    Node current;
    Node r;
    double miss_cnt;
    double cnt;
    int length;
    private static class Node{
        int page;
        int access_bit;
        int modification_bit;
        Node next;
        public Node(int access_bit,int modification_bit)
        {
            this.page=-1;
            this.access_bit=access_bit;
            this.modification_bit=modification_bit;
            this.next=null;
        }
        public Node()
        {
            this.next=null;
        }
    }
    public void init(Clock3 clock1,int length)
    {
        clock1.head=new Clock3.Node();
        clock1.current= clock1.head;
        clock1.length=length;
        clock1.cnt=0;
        clock1.miss_cnt=0;
        for(int i=0;i<length;i++)
        {
            Clock3.Node p=new Clock3.Node(0,0);
            clock1.current.next=p;
            clock1.current=p;
        }
        clock1.current.next=clock1.head.next;
        r=clock1.head.next;
    }
    public boolean if_miss(Clock3 clock1,int page)
    {
        Clock3.Node l=clock1.head.next;
        for(int i=0;i< clock1.length;i++)
        {
            if(l.page==page)
            {
                l.access_bit=1;
                return false;
            }
            l=l.next;
        }
        return true;
    }
    public String page_output(Clock3 clock1)
    {
        Clock3.Node l= clock1.head.next;
        String str;
        //System.out.println("当前页面有(-1表示空的物理块):");
        str="当前页面有(-1表示空的物理块)：\n";
        for(int i=0;i< clock1.length;i++)
        {
            //System.out.print(l.page+" ");
            str=str+l.page+" ";
            l=l.next;
        }
        //System.out.println("");
        str=str+"\n";
        return str;
    }
    public void page_replace(Clock3 clock1,int page1)
    {
        //int page1;
        //System.out.println("请输入页号:");
        //Scanner scanner=new Scanner(System.in);
        //page1=scanner.nextInt();
        if(clock1.if_miss(clock1,page1))//若有缺页，则进行clock页面置换
        {
            while(true)
            {
                if(r.access_bit==0)
                {
                    r.page=page1;
                    r=r.next;//换页之后指针指向下一个节点
                    clock1.cnt++;
                    clock1.miss_cnt++;
                    break;
                }
                else if(r.access_bit==1)
                {
                    r.access_bit=0;
                    r=r.next;
                }
            }
        }
        else
        {
            //System.out.println("没有缺页");
            clock1.cnt++;
        }
        //clock1.page_output(clock1);

    }
}
