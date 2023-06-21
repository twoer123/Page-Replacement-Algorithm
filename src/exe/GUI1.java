package exe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class GUI1 extends JFrame {
    GUI1.MainWindow mainWindow;
    public static void main(String args[])
    {
        GUI1 gui=new GUI1();
        gui.setTitle("ҳ���û��㷨");
    }
    GUI1()
    {
        setBounds(550,250,700,500);
        mainWindow=new GUI1.MainWindow();
        add(mainWindow);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    class MainWindow extends JPanel{
        JTabbedPane p;
        GUI1.Clock1View clock1View;
        GUI1.FIFOView fifoView;
        GUI1.OPTView optView;
        GUI1.LRUView lruView;
        GUI1.Clock2View clock2View;
        public MainWindow()
        {
            clock1View=new GUI1.Clock1View();
            fifoView=new GUI1.FIFOView();
            optView=new GUI1.OPTView();
            lruView=new GUI1.LRUView();
            clock2View=new GUI1.Clock2View();
            setLayout(new BorderLayout());
            p=new JTabbedPane();
            p.add("FIFO�㷨",fifoView);
            p.add("OPT�㷨",optView);
            p.add("LRU�㷨",lruView);
            p.add("clock�㷨",clock1View);
            p.add("�Ľ�clock�㷨",clock2View);
            p.validate();
            add(p,BorderLayout.CENTER);
        }
    }
    //clock�㷨��ѡ�����
    class Clock1View extends JPanel implements ActionListener {
        JLabel label1,label2,label3,label4;
        JTextField window_size,window_nums,miss_num,miss_rate;
        JScrollPane scroll;
        JTextArea output;
        JButton button1,button2,button3;
        Box boxV,boxH1,boxH2,boxH3,boxH4;
        Clock3 clock1=new Clock3();
        Clock1View(){
            label1=new JLabel("���ڴ�С:");
            label2=new JLabel("ҳ��:");
            label3=new JLabel("ȱҳ����:");
            label4=new JLabel("ȱҳ��:");
            window_size=new JTextField(3);
            window_nums=new JTextField(3);
            miss_num=new JTextField(3);
            miss_rate=new JTextField(3);
            output=new JTextArea(15,35);
            scroll=new JScrollPane(output);
            button1=new JButton("��ʼ");
            button2=new JButton("����");
            button3=new JButton("��ʼ��");
            boxH1=Box.createHorizontalBox();
            boxH2=Box.createHorizontalBox();
            boxH3=Box.createHorizontalBox();
            boxH4=Box.createHorizontalBox();
            boxV=Box.createVerticalBox();
            boxH1.add(label1);
            boxH1.add(window_size);
            boxH1.add(label2);
            boxH1.add(window_nums);
            boxH2.add(scroll,BorderLayout.CENTER);
            boxH3.add(label3);
            boxH3.add(miss_num);
            boxH3.add(label4);
            boxH3.add(miss_rate);
            boxH4.add(button1);
            boxH4.add(button2);
            boxH4.add(button3);
            boxV.add(boxH1);
            boxV.add(boxH2);
            boxV.add(boxH3);
            boxV.add(boxH4);
            add(boxV);
            button3.addActionListener(this);
            button1.addActionListener(this);
            button2.addActionListener(this);

        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==button3)
            {
                clock1.init(clock1,Integer.parseInt(window_size.getText()));
                output.append("��ʼ���ɹ�\n");
            }
            if(e.getSource()==button1)
            {
                DecimalFormat df=new DecimalFormat("0.00");//������λС��

                if(clock1.if_miss(clock1,Integer.parseInt(window_nums.getText())))
                {
                    output.append("����ȱҳ��");
                }
                else
                {
                    output.append("δ����ȱҳ!");
                }
                clock1.page_replace(clock1,Integer.parseInt(window_nums.getText()));
                output.append(clock1.page_output(clock1));
                miss_num.setText(""+clock1.miss_cnt);
                miss_rate.setText(""+df.format(clock1.miss_cnt/clock1.cnt));
                window_nums.setText(null);
            }
            else if(e.getSource()==button2)
            {
                window_size.setText(null);
                window_nums.setText(null);
                miss_num.setText(null);
                miss_rate.setText(null);
                output.setText(null);
                clock1=new Clock3();
            }
        }

    }
    //FIFO�㷨ѡ�����
    class FIFOView extends JPanel implements ActionListener{
        JLabel label1,label2,label3,label4;
        JTextField window_size,window_nums,miss_num,miss_rate;
        JScrollPane scroll;
        JTextArea output;
        JButton button1,button2,button3;
        Box boxV,boxH1,boxH2,boxH3,boxH4;
       FIFO1 fifo=new FIFO1();
        FIFOView(){
            label1=new JLabel("���ڴ�С:");
            label2=new JLabel("Ҫ���ʵ�ҳ��(�ÿո����):");
            label3=new JLabel("ȱҳ����:");
            label4=new JLabel("ȱҳ��:");
            window_size=new JTextField(3);
            window_nums=new JTextField(6);
            miss_num=new JTextField(3);
            miss_rate=new JTextField(3);
            output=new JTextArea(15,35);
            scroll=new JScrollPane(output);
            button1=new JButton("��ʼ");
            button2=new JButton("����");
            //button3=new JButton("��ʼ��");
            boxH1=Box.createHorizontalBox();
            boxH2=Box.createHorizontalBox();
            boxH3=Box.createHorizontalBox();
            boxH4=Box.createHorizontalBox();
            boxV=Box.createVerticalBox();
            boxH1.add(label1);
            boxH1.add(window_size);
            boxH1.add(label2);
            boxH1.add(window_nums);
            boxH2.add(scroll,BorderLayout.CENTER);
            boxH3.add(label3);
            boxH3.add(miss_num);
            boxH3.add(label4);
            boxH3.add(miss_rate);
            boxH4.add(button1);
            boxH4.add(button2);
            //boxH4.add(button3);
            boxV.add(boxH1);
            boxV.add(boxH2);
            boxV.add(boxH3);
            boxV.add(boxH4);
            add(boxV);
            //button3.addActionListener(this);
            button1.addActionListener(this);
            button2.addActionListener(this);

        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==button1)
            {
                DecimalFormat df=new DecimalFormat("0.00");//������λС��
                fifo.cnt=0;
                fifo.fifo(window_nums.getText(),Integer.parseInt(window_size.getText()));
                output.append(fifo.str);
                miss_num.setText(""+df.format(fifo.cnt));
                miss_rate.setText(""+df.format(fifo.rate));
            }
            else if(e.getSource()==button2)
            {
                window_size.setText(null);
                window_nums.setText(null);
                miss_num.setText(null);
                miss_rate.setText(null);
                output.setText(null);
                fifo=new FIFO1();
            }
        }
    }
    //OPT�㷨ѡ�����
    class OPTView extends JPanel implements ActionListener{
        JLabel label1,label2,label3,label4;
        JTextField window_size,window_nums,miss_num,miss_rate;
        JScrollPane scroll;
        JTextArea output;
        JButton button1,button2;
        Box boxV,boxH1,boxH2,boxH3,boxH4;
        OPT1 opt=new OPT1();
        OPTView(){
            label1=new JLabel("���ڴ�С:");
            label2=new JLabel("Ҫ���ʵ�ҳ��(�ÿո����):");
            label3=new JLabel("ȱҳ����:");
            label4=new JLabel("ȱҳ��:");
            window_size=new JTextField(3);
            window_nums=new JTextField(6);
            miss_num=new JTextField(3);
            miss_rate=new JTextField(3);
            output=new JTextArea(15,35);
            scroll=new JScrollPane(output);
            button1=new JButton("��ʼ");
            button2=new JButton("����");
            //button3=new JButton("��ʼ��");
            boxH1=Box.createHorizontalBox();
            boxH2=Box.createHorizontalBox();
            boxH3=Box.createHorizontalBox();
            boxH4=Box.createHorizontalBox();
            boxV=Box.createVerticalBox();
            boxH1.add(label1);
            boxH1.add(window_size);
            boxH1.add(label2);
            boxH1.add(window_nums);
            boxH2.add(scroll,BorderLayout.CENTER);
            boxH3.add(label3);
            boxH3.add(miss_num);
            boxH3.add(label4);
            boxH3.add(miss_rate);
            boxH4.add(button1);
            boxH4.add(button2);
            //boxH4.add(button3);
            boxV.add(boxH1);
            boxV.add(boxH2);
            boxV.add(boxH3);
            boxV.add(boxH4);
            add(boxV);
            //button3.addActionListener(this);
            button1.addActionListener(this);
            button2.addActionListener(this);

        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==button1)
            {
                DecimalFormat df=new DecimalFormat("0.00");//������λС��
                opt.cnt=0;
                opt.opt(window_nums.getText(),Integer.parseInt(window_size.getText()));
                output.append(opt.str);
                miss_num.setText(""+df.format(opt.cnt));
                miss_rate.setText(""+df.format(opt.rate));
            }
            else if(e.getSource()==button2)
            {
                window_size.setText(null);
                window_nums.setText(null);
                miss_num.setText(null);
                miss_rate.setText(null);
                output.setText(null);
                opt=new OPT1();
            }
        }

    }
    //LRU�㷨ѡ�����
    class LRUView extends JPanel implements ActionListener{
        JLabel label1,label2,label3,label4;
        JTextField window_size,window_nums,miss_num,miss_rate;
        JScrollPane scroll;
        JTextArea output;
        JButton button1,button2;
        Box boxV,boxH1,boxH2,boxH3,boxH4;
        LRU1 lru=new LRU1();
        LRUView(){
            label1=new JLabel("���ڴ�С:");
            label2=new JLabel("Ҫ���ʵ�ҳ��(�ÿո����):");
            label3=new JLabel("ȱҳ����:");
            label4=new JLabel("ȱҳ��:");
            window_size=new JTextField(3);
            window_nums=new JTextField(6);
            miss_num=new JTextField(3);
            miss_rate=new JTextField(3);
            output=new JTextArea(15,35);
            scroll=new JScrollPane(output);
            button1=new JButton("��ʼ");
            button2=new JButton("����");
            //button3=new JButton("��ʼ��");
            boxH1=Box.createHorizontalBox();
            boxH2=Box.createHorizontalBox();
            boxH3=Box.createHorizontalBox();
            boxH4=Box.createHorizontalBox();
            boxV=Box.createVerticalBox();
            boxH1.add(label1);
            boxH1.add(window_size);
            boxH1.add(label2);
            boxH1.add(window_nums);
            boxH2.add(scroll,BorderLayout.CENTER);
            boxH3.add(label3);
            boxH3.add(miss_num);
            boxH3.add(label4);
            boxH3.add(miss_rate);
            boxH4.add(button1);
            boxH4.add(button2);
            //boxH4.add(button3);
            boxV.add(boxH1);
            boxV.add(boxH2);
            boxV.add(boxH3);
            boxV.add(boxH4);
            add(boxV);
            //button3.addActionListener(this);
            button1.addActionListener(this);
            button2.addActionListener(this);

        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==button1)
            {
                DecimalFormat df=new DecimalFormat("0.00");//������λС��
                lru.count=0;
                lru.sum=0;
                lru.test(window_nums.getText(),Integer.parseInt(window_size.getText()));
                output.append(lru.str);
                miss_num.setText(""+df.format(lru.count));
                miss_rate.setText(""+df.format(lru.rate));
            }
            else if(e.getSource()==button2)
            {
                window_size.setText(null);
                window_nums.setText(null);
                miss_num.setText(null);
                miss_rate.setText(null);
                output.setText(null);
                lru=new LRU1();
            }
        }
    }
    class Clock2View extends JPanel implements ActionListener{
        JLabel label1,label2,label3,label4,label5;
        JTextField window_size,window_nums,miss_num,miss_rate;
        JScrollPane scroll;
        JTextArea output;
        JButton button1,button2,button3;
        JRadioButton buttonY,buttonN;
        ButtonGroup group;
        Box boxV,boxH1,boxH2,boxH3,boxH4;
        Clock4 clock2=new Clock4();
        Clock2View(){
            label1=new JLabel("���ڴ�С:");
            label2=new JLabel("ҳ��:");
            label3=new JLabel("ȱҳ����:");
            label4=new JLabel("ȱҳ��:");
            label5=new JLabel("�Ƿ��޸�");
            window_size=new JTextField(3);
            window_nums=new JTextField(3);
            miss_num=new JTextField(3);
            miss_rate=new JTextField(3);
            output=new JTextArea(15,35);
            scroll=new JScrollPane(output);
            button1=new JButton("��ʼ");
            button2=new JButton("����");
            button3=new JButton("��ʼ��");
            buttonY=new JRadioButton("��");
            buttonN=new JRadioButton("��");
            group=new ButtonGroup();
            group.add(buttonY);
            group.add(buttonN);
            boxH1=Box.createHorizontalBox();
            boxH2=Box.createHorizontalBox();
            boxH3=Box.createHorizontalBox();
            boxH4=Box.createHorizontalBox();
            boxV=Box.createVerticalBox();
            boxH1.add(label1);
            boxH1.add(window_size);
            boxH1.add(label2);
            boxH1.add(window_nums);
            boxH1.add(label5);
            boxH1.add(buttonY);
            boxH1.add(buttonN);
            boxH2.add(scroll,BorderLayout.CENTER);
            boxH3.add(label3);
            boxH3.add(miss_num);
            boxH3.add(label4);
            boxH3.add(miss_rate);
            boxH4.add(button1);
            boxH4.add(button2);
            boxH4.add(button3);
            boxV.add(boxH1);
            boxV.add(boxH2);
            boxV.add(boxH3);
            boxV.add(boxH4);
            add(boxV);
            button3.addActionListener(this);
            button1.addActionListener(this);
            button2.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==button3)
            {
                clock2.init(clock2,Integer.parseInt(window_size.getText()));
                output.append("��ʼ���ɹ�\n");
            }
            if(e.getSource()==button1) {
                DecimalFormat df = new DecimalFormat("0.00");//������λС��
                //JRadioButton selectedRadio=new JRadioButton();
                //selectedRadio=(JRadioButton)group.getSelection();
                //String selection=selectedRadio.getText();
                String selection=null;
                if (buttonY.isSelected())
                {
                    selection="y";
                    if (clock2.if_miss(clock2, Integer.parseInt(window_nums.getText()), selection))
                    {
                        output.append("����ȱҳ��");
                    }
                    else
                    {
                        output.append("δ����ȱҳ!");
                    }
                    clock2.page_replace(clock2, Integer.parseInt(window_nums.getText()), selection);
                    output.append(clock2.page_output(clock2));
                    miss_num.setText("" + clock2.miss_cnt);
                    miss_rate.setText("" + df.format(clock2.miss_cnt / clock2.cnt));
                    window_nums.setText(null);
                }
                else if(buttonN.isSelected())
                {
                    selection="n";
                    if (clock2.if_miss(clock2, Integer.parseInt(window_nums.getText()), selection))
                    {
                        output.append("����ȱҳ��");
                    }
                    else
                    {
                        output.append("δ����ȱҳ!");
                    }
                    clock2.page_replace(clock2, Integer.parseInt(window_nums.getText()), selection);
                    output.append(clock2.page_output(clock2));
                    miss_num.setText("" + clock2.miss_cnt);
                    miss_rate.setText("" + df.format(clock2.miss_cnt / clock2.cnt));
                    window_nums.setText(null);
                }
                System.out.println(selection);
            }
            else if(e.getSource()==button2)
            {
                window_size.setText(null);
                window_nums.setText(null);
                miss_num.setText(null);
                miss_rate.setText(null);
                output.setText(null);
                clock2=new Clock4();
            }
        }

    }
}
