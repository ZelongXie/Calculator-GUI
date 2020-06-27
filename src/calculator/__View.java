package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static calculator.__Controller.addListener;

public class __View {
    public static JFrame frame = new JFrame();
    public final static String[] keys = {"%", "CE", "C", "Back", "1⁄x", "x²", "√x", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
    public static MyButton[] buttons = new MyButton[keys.length];  //计算器上按键的按钮
    public static JTextField resultText = new JTextField("0");  //显示计算结果文本框

    public __View() {
        init();  // 初始化计算器
        addListener();
    }

    /**
     * 初始化计算器
     */
    private void init() {
        Color color1 = new Color(200, 200, 200);  //背景颜色
        Color color2 = new Color(140, 140, 140);  //等于号专属颜色
        Color color3 = new Color(230, 230, 230);  //功能键和运算符颜色
        Color color4 = new Color(240, 240, 240);  //数字颜色
        // 建立一个画板放文本框
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());
        textPanel.add(resultText);
        resultText.setFont(new Font("楷体", Font.BOLD, 42));  //设置文本框中文字的字体以及大小，加粗
        resultText.setHorizontalAlignment(JTextField.RIGHT);  //文本框中的内容采用右对齐方式
        resultText.setEditable(false);  //不能修改结果文本框
        resultText.setBorder(null);  //删除文本框的边框
        // 设置文本框背景颜色
        resultText.setBackground(color1);
        // 初始化计算器上键的按钮，将键放在一个画板内
        JPanel keysPanel = new JPanel();
        // 用网格布局器，6行，4列的网格，网格之间的水平方向垂直方向间隔均为2个像素
        keysPanel.setLayout(new GridLayout(6, 4, 2, 2));
        //初始化功能按钮
        for (int i = 0; i < 8; i++) {
            buttons[i] = new MyButton(keys[i], color3);
            keysPanel.add(buttons[i]);
            buttons[i].setBackground(color3);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false);  //去除按钮的边框
        }
        //初始化运算符及数字键按钮
        for (int i = 8; i < keys.length; i++) {
            if ((i + 1) % 4 == 0) buttons[i] = new MyButton(keys[i], color3);
            else buttons[i] = new MyButton(keys[i], color4);
            keysPanel.add(buttons[i]);
            buttons[i].setForeground(Color.black);
            buttons[i].setFont(new Font(Font.SERIF, Font.PLAIN, 18));
            buttons[i].setBorderPainted(false);  //去除按钮的边框
        }
        buttons[23].setBackground(color2);  // '='符键用特殊颜色
        keysPanel.setBackground(color1);
        //将文本框所在的面板放在北部，将keysPanel面板放在计算器的中部
        frame.getContentPane().add("North", textPanel);
        frame.getContentPane().add("Center", keysPanel);
        //设置两个面板的边框，尽量还原win10计算器
        textPanel.setBorder(BorderFactory.createMatteBorder(25, 3, 1, 3, color1));
        keysPanel.setBorder(BorderFactory.createMatteBorder(6, 3, 3, 3, color1));
        ImageIcon imageIcon = new ImageIcon("images/1.ico");
        frame.setIconImage(imageIcon.getImage());
        frame.setTitle("计算器");
        frame.setSize(360, 450);
        frame.setLocation(500, 300);
        frame.setResizable(true);  // 允许修改计算器窗口的大小
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
