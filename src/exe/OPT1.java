package exe;



import java.util.Arrays;

public class OPT1 {
    String str;
    double cnt=0,rate=0;
    public void opt(String input,int page)
    {
        String numbers[]=input.split(" ");//�������ַ���������
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
            if(index1< window.length)//ûװ��
            {
                for(int j=0;j<=index1;j++)
                {
                    if(work[i]==window[j])//��һ����Ԫ��
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)//û��һ����Ԫ��
                {
                    window[index1++]=work[i];
                    cnt++;
                }
            }
            else //װ����
            {
                for (int j = 0; j <window.length; j++)
                {
                    if (work[i] == window[j]) //��һ����Ԫ��
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0)//û��һ����Ԫ�ؽ���OPT�û�
                {

                    for (int k = 0; k < page; k++)//����Ӧ���ȼ�//���ֲ���//todo
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
                            priority[k]= work.length+1;//����ҵ������û�и�ҳ���ͽ���ҳ����Ϊ���
                        }
                    }
                    //�ҵ�priority����������Ԫ��
                    int max = priority[0],maxIndex=0;
                    for (int m = 1; m < priority.length; m++)
                    {
                        if (priority[m] > max)
                        {
                            max = priority[m];
                            maxIndex=m;
                        }
                    }
                    //�û������Ԫ�ض�Ӧ��window[]�е���
                    window[maxIndex]=work[i];
                    cnt++;
                }
            }
        }
        str="�������ǰҳ����(-1��ʾ�յ������):\n";
        for(int i=0;i< window.length;i++)
        {
            str=str+window[i]+" ";
        }
        str=str+"\n";
        rate=cnt/ work.length;
    }
}
