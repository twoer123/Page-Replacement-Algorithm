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
            //System.out.println("请输入一串数字用空格隔开:");
            //Scanner scanner =new Scanner(System.in);
            //String input=scanner.nextLine();
            String numbers[]=input.split(" ");//存贮到字符串数组中
            int work[]=new int[numbers.length];
            for(int i=0;i<numbers.length;i++)
            {
                work[i]=Integer.parseInt(numbers[i]);
            }
            //System.out.println("进程将要访问的页面是:");
            //for(int i=0;i<work.length;i++)
            //{
                //System.out.print(work[i]+" ");
            //}
            //System.out.println("\n请输入为该进程分配的页框大小：");
            //scanner=new Scanner(System.in);
            //System.out.println("页框的大小为："+page);
            int window[]=new int[page];
            Arrays.fill(window,-1);
            int j=0;
            boolean if_same=false;
            for(int i=0;i<work.length;i++)
            {
                for (int m = 0; m < window.length; m++) //判断页框中是否有该页
                {
                    if(window[m]==work[i])
                    {
                        if_same=true;
                    }
                }
                if(!if_same)//发生缺页
                {
                    window[j % page] = work[i];
                    cnt++;
                    j++;
                }
                if_same=false;//跳过缺页条件后要将其置为假下次循环用
            }
            //System.out.println("访问完之后的页框中的访问页是：");
            str="访问完之后的页框中的访问页是(-1表示空的物理块)：\n";
            for(int c=0;c<page;c++)
            {
                //System.out.print(window[c]+" ");
                str=str+window[c]+" ";
            }
            str=str+"\n";


            //System.out.println("\n缺页次数为："+cnt);
            rate=cnt/work.length;
            //System.out.println("缺页率为"+rate);
        }
}
