package exe;


public class LRU1 {
    int sum=0;    //�����洢ҳ������ô���ҳ��Ÿ���
    double count=0;//�����洢ȱҳ��
    double rate=0;
    String str;
    void test(String input,int page){
        int list=0;   //������¼��ʹ�õ������ĸ���
        int flag=1;   //������¼��ҳ������������Ƿ���ڣ�������Ϊ0������Ϊ1��
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("����������������");
        //int n=scanner.nextInt();
        int[] arr=new int[page];   //�����洢��ҳ���
        int[] t=new int[page];     //��Ӧҳ������ϴα�����������������ʱ��
        //System.out.println("������ҳ������ô����ÿո��������-1��ʾ��������");
        String numbers[]=input.split(" ");//�������ַ���������
        for(int i=0;i< numbers.length;i++)
        {
            System.out.println(numbers[i]);
        }
        int work[]=new int[numbers.length];
        for(int i=0;i<numbers.length;i++)
        {
            work[i]=Integer.parseInt(numbers[i]);
        }
        //while(scanner.hasNext()){
        for(int k=0;k< work.length;k++)
        {
            int num=work[k];//ҳ���
            //if(num==-1)break;   //��-1��ʾ����
            if(list<page)
            {         //�����δȫ��ռ��
                for(int i=0;i<list;i++)
                {
                    if(arr[i]==num)
                    {  //�жϸ�ҳ����Ƿ����
                        t[i]=0;
                        flag=0;
                    }
                    else t[i]++;
                }
                if(flag==1) {   //ҳ��Ų�����ʱ
                    arr[list] = num;
                    t[list] = 0;
                    list++;
                    count++;
                }
                else flag=1;
            }
            else
            {         //�������ȫ��ռ��
                for(int i=0;i<page;i++)
                {
                    if(arr[i]==num)
                    {  //�жϸ�ҳ����Ƿ����
                        t[i]=0;
                        flag=0;
                    }
                    else t[i]++;
                }
                if(flag==1) {   //ҳ��Ų�����ʱ
                    int max=0;
                    for(int j=1;j<page;j++){
                        if(t[j]>t[max])max=j;   //�������δʹ�õ�ҳ��
                    }
                    arr[max]=num;
                    t[max]=0;//����ҳ���û�����µ�ҳ��ķ���ʱ����Ϊ0
                    count++;
                }
                else flag=1;
            }
            sum++;
        }
        //scanner.close();
        rate=count/sum;
        //System.out.println("ȱҳ��Ϊ��"+count+" ȱҳ��Ϊ��"+count/sum*100+"%");
        //System.out.print("��ǰ�����洢��ҳ��Ϊ��");
        str="��ǰ�����洢��ҳ��Ϊ��\n";
        for(int i=0;i<list;i++){
            //System.out.print(arr[i]+" ");
            str=str+arr[i]+" ";
        }
        str=str+"\n";
    }
}
