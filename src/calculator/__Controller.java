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
                } else if (command.equals(keys[0]) || command.equals(keys[4]) || command.equals(keys[5]) ||
                        command.equals(keys[6]) || command.equals(keys[20])) {
                    // 用户按了只需一个数的运算键（求倒数，%，开方，平方，取正负数）
                    singleOperator(command);
                } else {
                    doubleOperator(command);
                }
            });
        }
    }


}