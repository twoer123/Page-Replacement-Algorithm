package exe;


public class LRU1 {
    int sum=0;    //用来存储页面号引用串的页面号个数
    double count=0;//用来存储缺页数
    double rate=0;
    String str;
    void test(String input,int page){
        int list=0;   //用来记录已使用的物理块的个数
        int flag=1;   //用来记录新页面在物理块中是否存在（不存在为0，存在为1）
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("请输入物理块个数：");
        //int n=scanner.nextInt();
        int[] arr=new int[page];   //物理块存储的页面号
        int[] t=new int[page];     //对应页面号自上次被访问以来所经历的时间
        //System.out.println("请输入页面号引用串（用空格隔开，用-1表示结束）：");
        String numbers[]=input.split(" ");//存贮到字符串数组中
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
            int num=work[k];//页面号
            //if(num==-1)break;   //用-1表示结束
            if(list<page)
            {         //物理块未全部占用
                for(int i=0;i<list;i++)
                {
                    if(arr[i]==num)
                    {  //判断该页面号是否存在
                        t[i]=0;
                        flag=0;
                    }
                    else t[i]++;
                }
                if(flag==1) {   //页面号不存在时
                    arr[list] = num;
                    t[list] = 0;
                    list++;
                    count++;
                }
                else flag=1;
            }
            else
            {         //物理块已全部占用
                for(int i=0;i<page;i++)
                {
                    if(arr[i]==num)
                    {  //判断该页面号是否存在
                        t[i]=0;
                        flag=0;
                    }
                    else t[i]++;
                }
                if(flag==1) {   //页面号不存在时
                    int max=0;
                    for(int j=1;j<page;j++){
                        if(t[j]>t[max])max=j;   //查找最久未使用的页面
                    }
                    arr[max]=num;
                    t[max]=0;//讲旧页面置换后把新的页面的访问时间置为0
                    count++;
                }
                else flag=1;
            }
            sum++;
        }
        //scanner.close();
        rate=count/sum;
        //System.out.println("缺页数为："+count+" 缺页率为："+count/sum*100+"%");
        //System.out.print("当前物理块存储的页面为：");
        str="当前物理块存储的页面为：\n";
        for(int i=0;i<list;i++){
            //System.out.print(arr[i]+" ");
            str=str+arr[i]+" ";
        }
        str=str+"\n";
    }
}
