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