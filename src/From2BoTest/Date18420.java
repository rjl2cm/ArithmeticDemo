package From2BoTest;

import java.util.Arrays;

/**
 * @Author: franer
 * @Description:
 * expression ::= value | operation
 * operation ::= expression '?' expression | expression '&' expression
 * value ::= an integer //一个整数值
 * exp1 ? exp2 ::= exp1*exp1+exp2
 * exp1 & exp2 ::= exp1*exp1-exp2
 * 例如：2?3&40&5 表达式的值， 先计算 2?3=2*2+3=7 然后计算 7&40=7*2-40=9 接着计算 9&5=9*9-5=76
 * @Date: Create in 2018/四月/20 : 17:40
 * @Modify:
 */
public class Date18420 {

    public static void main(String[] args) throws Exception{

        System.out.println(ExpressionExecutor.getInstance("2?10?2&10").getResult());

    }
}

class ExpressionExecutor{

    static ExpressionExecutor mInstance;

    static {
        mInstance = new ExpressionExecutor();
    }

    private String express;

    public static ExpressionExecutor getInstance(String express) throws Exception{
        mInstance.express = express.replace("？", "?");
        if (!isExpressLegal(mInstance.express)){
            throw new Exception("输入不合法");
        }
        return mInstance;
    }

    //做一些表达式是否合法的检测的检测
    private static boolean isExpressLegal(String express) {
        //TODO 先不写了，总是通过表达式合法检测
        return true;
    }

    public int getResult(){

        return Integer.parseInt(executePart(express));
    }

    private String executePart(String s) {//以一个最小括号为单位进行计算,如果全局没有括号，直接计算所有值
        //获取第一个"("
        int startIndex = s.indexOf("(");
        if (startIndex == -1){
            //没有括号了
            //首先做一些预处理
            s = s.replace("&", "split&split").replace("?", "split?split");
            String[] parts = s.split("split");
            if (parts.length % 2 != 1){
                //讲道理，这种情况应该不会出现
                System.out.println("可是不会出现的情况还真TM出现了！");
            } else{
                int index = 0;
                int result = 0;
                int addition = 0;
                String sign = "";
                result = Integer.parseInt(parts[index]);//先把第一个数作为结果
                while(index < parts.length - 1){
                    sign = parts[index+1];
                    addition = Integer.parseInt(parts[index+2]);
                    switch(sign){
                        case "?"://exp1 ? exp2 ::= exp1*exp1+exp2
                            result = result * result + addition;
                            break;
                        case "&"://exp1 & exp2 ::= exp1*exp1-exp2
                            result = result * result - addition;
                            break;
                        default://讲道理这种情况是不会出现的！
                            System.out.println("可是这TM这的又出现了！");
                            break;
                    }
                    index += 2;
                }//while
                return result + "";
            }
        } else{
            //有括号
            int endIndex = s.indexOf(")");
            String temp = s.substring(startIndex+1, endIndex);
            s = s.replace("("+temp+")", executePart(temp));//把括号里面的计算出来
            while(!s.matches("-?[0-9]+")){//express还不能转换成一个int
                s = executePart(s);
            }
        }
        return s;
    }
}