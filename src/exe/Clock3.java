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
        //System.out.println("��ǰҳ����(-1��ʾ�յ������):");
        str="��ǰҳ����(-1��ʾ�յ������)��\n";
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
        //System.out.println("������ҳ��:");
        //Scanner scanner=new Scanner(System.in);
        //page1=scanner.nextInt();
        if(clock1.if_miss(clock1,page1))//����ȱҳ�������clockҳ���û�
        {
            while(true)
            {
                if(r.access_bit==0)
                {
                    r.page=page1;
                    r=r.next;//��ҳ֮��ָ��ָ����һ���ڵ�
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
            //System.out.println("û��ȱҳ");
            clock1.cnt++;
        }
        //clock1.page_output(clock1);

    }
}
