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
                l.access_bit=1;//�������򽫷���λ��Ϊ1
                if(modification=="y")
                {
                    l.modification_bit=1;//�޸ĺ�ͽ��޸�λ��Ϊ1
                }
                else if(modification=="n")
                {
                    l.modification_bit=0;//δ�޸ľͽ��޸�λ��Ϊ0
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
        //System.out.println("��ǰҳ���У�");
        String str="��ǰҳ����(-1���������Ϊ��)��";
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
        //System.out.println("������ҳ��:");
        //Scanner scanner=new Scanner(System.in);
        //page=scanner.nextInt();
        //System.out.println("�Ƿ��޸ģ�y/n��");
        //scanner=new Scanner(System.in);
        //modification=scanner.next();
        if(clock2.if_miss(clock2,page,modification))//����ȱҳ����ҳ���û��㷨
        {
            boolean j=true;
            for(int i=0;i<clock2.length;i++)//��һ��ɨ�裨A=0,M=0��
            {
                if(clock2.r.access_bit==0&&clock2.r.modification_bit==0)
                {
                    clock2.r.page=page;//�ͽ��任ҳ
                    clock2.cnt++;
                    clock2.miss_cnt++;
                    if(modification=="y")//����ҳ��Ҫ�޸ģ��ͽ����޸�λ��Ϊ1
                    {
                        clock2.r.modification_bit=1;
                    }
                    else if(modification=="n")//������Ϊ0
                    {
                        clock2.r.modification_bit=0;
                    }
                    clock2.r= clock2.r.next;  //������֮��ָ��ָ����һ��
                    j=false;//��j��Ϊfalse����������������
                    break;
                }
                clock2.r=r.next;
            }
            if(j)//����һ��ɨ��ʧ�������ڶ���ѭ��
            {
                for(int i=0;i<clock2.length;i++)//�ڶ���ɨ��(A==0,M=1),����A=1��Ϊ0
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==1)
                    {
                        clock2.r.page=page;//�ͽ��任ҳ
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//����ҳ��Ҫ�޸ģ��ͽ����޸�λ��Ϊ1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//������Ϊ0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r.access_bit=0;//������λΪ1����Ϊ0
                        clock2.r=r.next;  //������֮��ָ��ָ����һ��
                        j=false;//��j��Ϊfalse����������������
                        break;
                    }
                    else
                    {
                        clock2.r.access_bit=0;//��ɨ���ķ���λ����Ϊ0
                        clock2.r=clock2.r.next;
                    }

                }
            }
            if(j)//���ڶ���ɨ��ʧ�ܾͽ��뵽������ѭ��
            {
                for(int i=0;i<clock2.length;i++)//������ɨ��(A=0,M=0)
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==0)
                    {
                        clock2.r.page=page;//�ͽ��任ҳ
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//����ҳ��Ҫ�޸ģ��ͽ����޸�λ��Ϊ1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//������Ϊ0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r= clock2.r.next;  //������֮��ָ��ָ����һ��
                        j=false;//��j��Ϊfalse����������������
                        break;
                    }
                    clock2.r=clock2.r.next;
                }
            }
            if(j)//��������ɨ��ʧ�ܾͽ��뵽���Ĵ�ѭ��
            {
                for(int i=0;i<clock2.length;i++)//���е��Ĵ�ɨ���ң�A=0��M=1���˴�ѭ��һ�����ҵ�
                {
                    if(clock2.r.access_bit==0&&clock2.r.modification_bit==1)
                    {
                        clock2.r.page=page;//�ͽ��任ҳ
                        clock2.cnt++;
                        clock2.miss_cnt++;
                        if(modification=="y")//����ҳ��Ҫ�޸ģ��ͽ����޸�λ��Ϊ1
                        {
                            clock2.r.modification_bit=1;
                        }
                        else if(modification=="n")//������Ϊ0
                        {
                            clock2.r.modification_bit=0;
                        }
                        clock2.r= clock2.r.next;  //������֮��ָ��ָ����һ��
                        j=false;//��j��Ϊfalse����������������
                        break;
                    }
                    clock2.r=clock2.r.next;
                }
            }
            clock2.page_output(clock2);
        }
        else
        {
            //System.out.println("δ����ȱҳ");
            clock2.cnt++;
            clock2.page_output(clock2);
        }
        //}
    }
}
