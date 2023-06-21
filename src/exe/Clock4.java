package exe;
import java.util.Scanner;

public class Clock4 {
    Node head;
    Node current;
    Node r;
    double miss_cnt;
    double cnt;
    int length;
    private static class Node
    {
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
    public void init(Clock4 clock2,int length)
    {
        clock2.head=new Clock4.Node();
        clock2.current= clock2.head;
        clock2.length=length;
        clock2.cnt=0;
        clock2.miss_cnt=0;
        for(int i=0;i<length;i++)
        {
            Clock4.Node p=new Clock4.Node(0,0);
            clock2.current.next=p;
            clock2.current=p;
        }
        clock2.current.next=clock2.head.next;
        r=clock2.head.next;
    }
    public boolean if_miss(Clock4 clock2,int page,String modification)
    {
        Clock4.Node l=clock2.head.next;
        for(int i=0;i< clock2.length;i++)
        {
            if(l.page==page)
            {
                l.access_bit=1;//若存在则将访问位置为1
                if(modification=="y")
                {
                    l.modification_bit=1;//修改后就将修改位置为1
                }
                else if(modification=="n")
                {
                    l.modification_bit=0;//未修改就将修改位置为0
                }
                return false;
            }
            l=l.next;
        }
        return true;
    }
    public String page_output(Clock4 clock2)
    {
        Clock4.Node l= clock2.head.next;
        //System.out.println("当前页面有：");
        String str="当前页面有(-1代表物理块为空)：";
        for(int i=0;i<clock2.length;i++)
        {
            //System.out.println(l.page+" ");
            str=str+l.page+" ";
            l=l.next;
        }
        //System.out.println("");
        return str+"\n";
    }
    public void page_replace(Clock4 clock2,int page,String modification)
    {
        Clock4.Node l=clock2.head.next;
        //while(true)
        //{
        //int page;
        //String modification;
        //System.out.println("请输入页号:");
        //Scanner scanner=new Scanner(System.in);
        //page=scanner.nextInt();
        //System.out.println("是否修改（y/n）");
        //scanner=new Scanner(System.in);
        //modification=scanner.next();
        if(clock2.if_miss(clock2,page,modification))//发生缺页进行页面置换算法
        {
            boolean j=true;
            for(int i=0;i<clock2.length;i++)//第一轮扫描（A=0,M=0）
            {
                if(clock2.r.access_bit==0&&clock2.r.modification_bit==0)
                {
                    clock2.r.page=page;//就将其换页
                    clock2.cnt++;
                    clock2.miss_cnt++;
                    if(modification=="y")//若此页需要修改，就将其修改位设为1
                    {
                        clock2.r.modification_bit=1;
                    }
                    else if(modification=="n")//否则设为0
                    {
                        clock2.r.modification_bit=0;
                    }
                    clock2.r= clock2.r.next;  //操作完之后将指针指向下一个
                    j=false;//将j置为false跳过下面的条件语句
                    break;
                }
                clock2.r=r.next;
            }
            if(j)//若第一轮扫描失败则进入第二次循环
            {
                for(int i=0;i<clock2.length;i++)//第二轮扫描(A==0,M=1),并将A=1置为0
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==1)
                    {
                        clock2.r.page=page;//就将其换页
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//若此页需要修改，就将其修改位设为1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//否则设为0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r.access_bit=0;//将访问位为1的置为0
                        clock2.r=r.next;  //操作完之后将指针指向下一个
                        j=false;//将j置为false跳过下面的条件语句
                        break;
                    }
                    else
                    {
                        clock2.r.access_bit=0;//将扫过的访问位都置为0
                        clock2.r=clock2.r.next;
                    }

                }
            }
            if(j)//若第二次扫描失败就进入到第三次循环
            {
                for(int i=0;i<clock2.length;i++)//第三次扫描(A=0,M=0)
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==0)
                    {
                        clock2.r.page=page;//就将其换页
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//若此页需要修改，就将其修改位设为1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//否则设为0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r= clock2.r.next;  //操作完之后将指针指向下一个
                        j=false;//将j置为false跳过下面的条件语句
                        break;
                    }
                    clock2.r=clock2.r.next;
                }
            }
            if(j)//若第三次扫描失败就进入到第四次循环
            {
                for(int i=0;i<clock2.length;i++)//进行第四次扫描找（A=0，M=1）此次循环一定能找到
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==1)
                    {
                        clock2.r.page=page;//就将其换页
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//若此页需要修改，就将其修改位设为1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//否则设为0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r= clock2.r.next;  //操作完之后将指针指向下一个
                        j=false;//将j置为false跳过下面的条件语句
                        break;
                    }
                    clock2.r=clock2.r.next;
                }
            }
            clock2.page_output(clock2);
        }
        else
        {
            //System.out.println("未发生缺页");
            clock2.cnt++;
            clock2.page_output(clock2);
        }
        //}
    }
}
