package exe;



import java.util.Arrays;

public class OPT1 {
    String str;
    double cnt=0,rate=0;
    public void opt(String input,int page)
    {
        String numbers[]=input.split(" ");//存贮到字符串数组中
        int work[]=new int[numbers.length];
        for(int i=0;i<numbers.length;i++)
        {
            work[i]=Integer.parseInt(numbers[i]);
        }
        int window[]=new int[page];
        int priority[]=new int[page];
        Arrays.fill(window,-1);
        int index1=0;
        for(int i=0;i<work.length;i++)
        {
            int flag=0;
            if(index1< window.length)//没装满
            {
                for(int j=0;j<=index1;j++)
                {
                    if(work[i]==window[j])//有一样的元素
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)//没有一样的元素
                {
                    window[index1++]=work[i];
                    cnt++;
                }
            }
            else //装满了
            {
                for (int j = 0; j <window.length; j++)
                {
                    if (work[i] == window[j]) //有一样的元素
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)//没有一样的元素进行OPT置换
                {

                    for (int k = 0; k < page; k++)//给对应优先级//二分查找//todo
                    {
                        boolean if_have=false;
                        for (int h =i; h < work.length; h++)
                        {
                            if (work[h] == window[k])
                            {
                                priority[k] = h;
                                if_have=true;
                                break;
                            }
                        }
                        if(!if_have)
                        {
                            priority[k]= work.length+1;//若作业队列中没有该页，就将该页设置为最大
                        }
                    }
                    //找到priority数组中最大的元素
                    int max = priority[0],maxIndex=0;
                    for (int m = 1; m < priority.length; m++)
                    {
                        if (priority[m] > max)
                        {
                            max = priority[m];
                            maxIndex=m;
                        }
                    }
                    //置换该最大元素对应的window[]中的数
                    window[maxIndex]=work[i];
                    cnt++;
                }
            }
        }
        str="访问完后当前页面有(-1表示空的物理块):\n";
        for(int i=0;i< window.length;i++)
        {
            str=str+window[i]+" ";
        }
        str=str+"\n";
        rate=cnt/ work.length;
    }
}
