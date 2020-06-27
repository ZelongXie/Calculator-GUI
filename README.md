# Java GUI实现基于MVC框架的计算器

* 本项目为我的Java期末课设

* 运行CalculatorMain.java即可

* 基于以下同学的代码
  * [Java实现仿win10计算器](https://blog.csdn.net/qq_43974000/article/details/104271426)
  * [用MVC模式重写简单的计算器](https://blog.csdn.net/jsjyyjs07/article/details/8894789)
  * [JButton 式样自定义](https://blog.csdn.net/yuanzihui/article/details/43935509)
  

* 下面是我当时的实验报告
  

## **一、实验目的**

通过本次实验，主要掌握以下知识点：

* 掌握JAVA Swing设计GUI界面的流程

* 掌握各种组件创建方法及应用

* 掌握事件处理机制

  

## **二、实验要求与内容**

​		**题目**：计算器

​		**设计内容**：设计一个图形界面（GUI）的计算器应用程序，完成简单的算术运算、

​		**设计要求**：设计一个图形界面（GUI）的计算器应用程序，完成加法、减法、乘法、除法等运算，且参与计算的数字和所得结果可以有小数点、正负号。

​		**学习要点**：本程序主要练习使用布局管理器设计一个计算器的界面，并练习使用事件监听器处理数据的输入，并完成相关的计算。



## **三、实验步骤与结果**

### 项目介绍



​		此项目是一个基于Java语言开发的计算器，实现了加、减、乘、除运算，求倒数、求平方根和求平方功能，并具有退格清零功能。

![](https://github.com/ZelongXie/Calculator-GUI/blob/master/images/1.png)



​		实现了按键鼠标悬浮变色。

![](https://github.com/ZelongXie/Calculator-GUI/blob/master/images/2.png)





### 需求分析



​	对项目需求做出以下分析：

1. 使用Java GUI设计计算器界面
2. 通过界面按钮，可以进行加、减、乘、除运算，求倒数、求平方根和求平方功能，并能将结果显示在界面中
3. 计算可以有小数点，和正负整数的计算
4. 有清零功能
5. 有回退一位和所有位功能
6. 鼠标悬浮在按键上会变色



### 设计思路



​		本项目使用MVC框架，**__Model.java**为模型层是负责处理按键按下后的封装方法，会将处理的结果传回View层，**__View.java**为视图层负责用户交互,将窗口和结果显示出来，**__Controller.java**为控制器层负责接受读取用户按键向Model层传递需处理的数据。



![](https://github.com/ZelongXie/Calculator-GUI/blob/master/images/3.png)



#### Model

* pressBackspace()：此方法为回退一格，将文本最后一个字符去掉，如果文本没有内容了，则初始化计算器的各种值，否则显示新的数字

* pressC()：此方法为处理C键被按下的事件，初始化计算器的各种值

* pressNumber()：此方法为处理数字键被按下的事件，若输入的为第一个数，则设置文本为数；若输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面；如果输入的不是小数点，则将数字附在结果文本框的后面

* singleOperator()：此方法为处理只需一个数的运算的运算符键被按下的事件，switch case匹配操作的运算符，判断操作是否合法后进行运算

* doubleOperator()：此方法为处理两个数的运算的运算符键被按下的事件，switch case匹配操作的运算符，判断操作是否合法后进行运算

* omitDecimal()：此方法为保留八位小数，被上述方法调用

* getNumberFromText()：此方法为从结果文本框中获取数字

#### View

​		程序使用GUI实现界面布局。

![](https://github.com/ZelongXie/Calculator-GUI/blob/master/images/4.png)



* init()方法首先创建两个JPanel画板对象，分别是放文本框的textPanel采用BorderLayout布局和放按键的keysPanel采用6行4列的GridLayout布局。
  * 初始化resultText（JTextField）后放入textPanel
  * 初始化buttons[]（MyButton）后放入keysPanel，其中buttons[]为MyButton数组
  * 最后将textPanel和keysPanel添加进frame（JFrame类），对frame初始化

* MyButton为定义的继承JButton的按钮类，添加了鼠标监听器当鼠标悬浮在按键上时变色：MyButton中注册了鼠标监听器addMouseListener：事件监听器中事件处理方法public void mouseEntered(MouseEvent *e*)重写了判断鼠标操作的方法对 isMouseEntered赋值。后面重写了paintComponent方法，依据isMouseEntered的值对按钮进行重绘

* 创建构造方法调用init()和__Controller中的方法对MyButton添加事件监听器

#### Controller

​		为各按钮添加事件监听器，判断用户按键后调用__Model中的运算方法

#### Main

​		在CalculatorMain类的main方法中new一个__View的对象开始运行



## 四、思考题

#### 1、什么是GUI？

* GUI全称是Graphical User Interface，即图形用户界面，即应用程序提供给用户操作的图形界面，包括窗口、菜单、按钮、工具栏和其它各种图形界面元素

#### 2、什么是事件处理？

* GUI是基于事件驱动的，即当用户与GUI交互时，这些构件就产生事件。例如，当移动鼠标、单击按钮、在文本框中输入字符、关闭一个窗口等，这些都是事件。Java对事件处理采用的是基于**事件源、事件对象和监听者**的委托事件处理模型；每个事件源可以发出若干种不同类型的事件。在程序中为每个事件源指定一个或者多个监听者，它可以对某种事件进行监听。如果某种事件发生，就调用相应监听者中的方法。

#### 3、Java语言包含哪些顶级容器？

* JFrame，JDialog，和JApplet



## 附录

### __Model.java

```java
package calculator;

import java.text.DecimalFormat;

import static calculator.__View.*;

public class __Model {

    private static boolean firstDigit = true;  // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private static double resultNum = 0.0000;   // 计算的中间结果
    private static String operator = "=";   // 当前运算的运算符（按键"C"时需要将其还原为"="）
    private static boolean operateValidFlag = true;   // 判断操作是否合法

    public static void pressBackspace() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);  // 退格，将文本最后一个字符去掉
            if (text.length() == 0) {
                // 如果文本没有内容了，则初始化计算器的各种值
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                // 显示新的文本
                resultText.setText(text);
            }
        }
    }

    /**
     * 处理C键被按下的事件
     */
    public static void pressC() {
        // 初始化计算器的各种值
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }

    /**
     * 处理数字键被按下的事件
     */
    public static void pressNumber(String key) {
        if (firstDigit) {
            // 输入的为第一个数
            resultText.setText(key);
        } else if ((key.equals(".")) && (!resultText.getText().contains("."))) {
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
            resultText.setText(resultText.getText() + ".");
        } else if (!key.equals(".")) {
            // 如果输入的不是小数点，则将数字附在结果文本框的后面
            resultText.setText(resultText.getText() + key);
        }
        firstDigit = false;
    }

    /**
     * 处理运算符键被按下的事件
     */
    //只需一个数的运算
    public static void singleOperator(String key) {
        operator = key;  // 运算符为用户按的按钮
        switch (operator) {
            case "1⁄x":
                // 倒数运算
                if (resultNum == 0) {
                    operateValidFlag = false;  //操作不合法
                    resultText.setText("零没有倒数");
                } else {
                    resultNum = 1 / getNumberFromText();
                    omitDecimal(resultNum);
                }
                break;
            case "√x":
                // 平方根运算
                if (resultNum < 0) {
                    operateValidFlag = false;  //操作不合法
                    resultText.setText("根号内不能为负");
                } else {
                    resultNum = Math.sqrt(getNumberFromText());
                    omitDecimal(resultNum);
                }
                break;
            case "x²":
                // 平方运算
                resultNum = getNumberFromText() * getNumberFromText();
                omitDecimal(resultNum);
                break;
            case "%":
                // 百分号运算，除以100
                resultNum = getNumberFromText() / 100;
                resultText.setText(String.valueOf(resultNum));
                break;
            case "+/-":
                // 正数负数运算
                resultNum = getNumberFromText() * (-1);
                if (operateValidFlag) {
                    // 操作合法的情况下，结果为小数保留小数点后4位，整数正常输出
                    omitDecimal(resultNum);
                }
                firstDigit = true;
                operateValidFlag = true;
                break;
        }
    }

    //需要两个数的运算
    public static void doubleOperator(String key) {
        switch (operator) {
            case "÷":
                // 除法运算
                // 如果当前结果文本框中的值等于0
                if (getNumberFromText() == 0.0) {
                    operateValidFlag = false;  //操作不合法
                    resultText.setText("除数不能为零");
                } else {
                    resultNum /= getNumberFromText();
                }
                break;
            case "+":
                // 加法运算
                resultNum += getNumberFromText();
                break;
            case "-":
                // 减法运算
                resultNum -= getNumberFromText();
                break;
            case "×":
                // 乘法运算
                resultNum *= getNumberFromText();
                break;
            case "=":
                // 赋值运算
                resultNum = getNumberFromText();
                break;
        }
        omitDecimal(resultNum);
        operator = key;  // 运算符为用户按的按钮
        firstDigit = true;
        operateValidFlag = true;
    }

    public static void omitDecimal(double resultNum) {
        long t1;
        double t2;
        t1 = (long) resultNum;
        t2 = resultNum - t1;
        if (t2 == 0) {
            resultText.setText(String.valueOf(t1));
        } else {
            resultText.setText(String.valueOf(new DecimalFormat("0.00000000").format(resultNum)));
        }
    }

    /**
     * 从结果文本框中获取数字
     */
    public static double getNumberFromText() {
        double result = 0;
        try {
            result = Double.parseDouble(resultText.getText());
        } catch (NumberFormatException ignored) {
        }
        return result;
    }


}
```



### __View.java

```java
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
        ImageIcon imageIcon=new ImageIcon("1.ico");
        frame.setIconImage(imageIcon.getImage());
        frame.setTitle("计算器");
        frame.setSize(360, 450);
        frame.setLocation(500, 300);
        frame.setResizable(true);  // 允许修改计算器窗口的大小
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

```





### __Controller.java

```java
package calculator;

import static calculator.__View.*;
import static calculator.__Model.*;

public class __Controller {
    public static void addListener() {
        // 为各按钮添加事件监听器
        for (int i = 0; i < keys.length; i++) {
            buttons[i].addActionListener(e -> {
                String command = e.getActionCommand();  // 获取事件源
                if (command.equals(keys[3])) {
                    // 用户按了"Back"键
                    pressBackspace();
                } else if (command.equals(keys[1])) {
                    // 用户按了"CE"键
                    resultText.setText("0");
                } else if (command.equals(keys[2])) {
                    // 用户按了"C"键
                    pressC();
                } else if ("0123456789.".contains(command)) {
                    // 用户按了数字键或者小数点键
                    pressNumber(command);
                } else if (command.equals(keys[0]) || command.equals(keys[4]) || command.equals(keys[5]) || command.equals(keys[6]) || command.equals(keys[20])) {
                    // 用户按了只需一个数的运算键（求倒数，%，开方，平方，取正负数）
                    singleOperator(command);
                } else {
                    doubleOperator(command);
                }
            });
        }
    }

}
```



### MyButton.java

```java
package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {
    private int isMouseEntered = 1;// 鼠标是否进入按钮
    Color color0;
    Color color1 = new Color(200, 200, 200);

    public MyButton(String buttonText, Color color) {
        super(buttonText);
        color0 = color;
        //添加鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //当鼠标进入时,鼠标进入状态改为TRUE，并重绘按钮
                isMouseEntered = 0;
                repaint();
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isMouseEntered = 1;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                isMouseEntered = 2;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMouseEntered = 0;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 绘制渐变底色
        switch (isMouseEntered) {
            case 0:
                this.setBackground(color1);
                break;
            case 1:
                this.setBackground(color0);
                break;
            case 2:
            default:
                break;
        }
        super.paintComponent(g);

    }

}
```

### CalculatorMain.java

```java
package calculator;

public class CalculatorMain {
    public CalculatorMain(){}
    public static void main(String[] args) {
        new __View();
    }
}
```



