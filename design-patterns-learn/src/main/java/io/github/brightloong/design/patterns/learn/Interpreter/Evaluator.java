package io.github.brightloong.design.patterns.learn.Interpreter;

import java.util.Map;
import java.util.Stack;

/**
 * @author BrightLoong
 * @date 2018/9/14 17:09
 * @description
 */
class Evaluator {
    private Expression syntaxTree;

    public Evaluator(String expression) {
        Stack<Expression> expressionStack = new Stack<Expression>();
        for (String token : expression.split(" ")) {
            if  (token.equals("+")) {
                Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop());
                expressionStack.push( subExpression );
            }
            else if (token.equals("-")) {
                // it's necessary remove first the right operand from the stack
                Expression right = expressionStack.pop();
                // ..and after the left one
                Expression left = expressionStack.pop();
                Expression subExpression = new Minus(left, right);
                expressionStack.push( subExpression );
            }
            else
                expressionStack.push( new Variable(token) );
        }
        syntaxTree = expressionStack.pop();
    }

    public int calculate(Map<String,Expression> context) {
        return syntaxTree.interpret(context);
    }
}
