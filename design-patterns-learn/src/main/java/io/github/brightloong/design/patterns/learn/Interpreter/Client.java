package io.github.brightloong.design.patterns.learn.Interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author BrightLoong
 * @date 2018/9/14 17:09
 * @description
 */
public class Client {
    public static void main(String[] args) {
        String expression = "w x z - +";
        Evaluator sentence = new Evaluator(expression);
        Map<String,Expression> variables = new HashMap<String,Expression>();
        variables.put("w", new Number(5));
        variables.put("x", new Number(10));
        variables.put("z", new Number(42));
        int result = sentence.calculate(variables);
        System.out.println(result);
    }
}
