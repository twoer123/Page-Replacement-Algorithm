package exe;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Arrays;

public class FIFO1 {
        String str;
        double cnt=0;
        double rate=0;
        public void fifo(String input,int page)
        {
            //System.out.println("������һ�������ÿո����:");
            //Scanner scanner =new Scanner(System.in);
            //String input=scanner.nextLine();
            String numbers[]=input.split(" ");//�������ַ���������
            int work[]=new int[numbers.length];
            for(int i=0;i<numbers.length;i++)
            {
                work[i]=Integer.parseInt(numbers[i]);
            }
            //System.out.println("���̽�Ҫ���ʵ�ҳ����:");
            //for(int i=0;i<work.length;i++)
            //{
                //System.out.print(work[i]+" ");
            //}
            //System.out.println("\n������Ϊ�ý��̷����ҳ���С��");
            //scanner=new Scanner(System.in);
            //System.out.println("ҳ��Ĵ�СΪ��"+page);
            int window[]=new int[page];
            Arrays.fill(window,-1);
            int j=0;
            boolean if_same=false;
            for(int i=0;i<work.length;i++)
            {
                for (int m = 0; m < window.length; m++) //�ж�ҳ�����Ƿ��и�ҳ
                {
                    if(window[m]==work[i])
                    {
                        if_same=true;
                    }
                }
                if(!if_same)//����ȱҳ
                {
                    window[j % page] = work[i];
                    cnt++;
                    j++;
                }
                if_same=false;//����ȱҳ������Ҫ������Ϊ���´�ѭ����
            }
            //System.out.println("������֮���ҳ���еķ���ҳ�ǣ�");
            str="������֮���ҳ���еķ���ҳ��(-1��ʾ�յ������)��\n";
            for(int c=0;c<page;c++)
            {
                //System.out.print(window[c]+" ");
                str=str+window[c]+" ";
            }
            str=str+"\n";


            //System.out.println("\nȱҳ����Ϊ��"+cnt);
            rate=cnt/work.length;
            //System.out.println("ȱҳ��Ϊ"+rate);
        }
}
