package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import Helpers.FunctionHelpers;
import TYPES.*;

public class AST_EXP_BINOP extends AST_EXP {
    int OP;
    public AST_EXP leftExp;
    public AST_EXP rightExp;

    public AST_EXP_BINOP(AST_EXP leftExp, AST_EXP rightExp, int OP) {

        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== exp -> exp BINOP exp\n");

        this.leftExp = leftExp;
        this.rightExp = rightExp;
        this.OP = OP;
    }

    public void PrintMe() {
        String sOP = "";

        if (OP == 0) {
            sOP = "+";
        }
        if (OP == 1) {
            sOP = "-";
        }
        if (OP == 2) {
            sOP = "*";
        }
        if (OP == 3) {
            sOP = "/";
        }
        if (OP == 4) {
            sOP = "<";
        }
        if (OP == 5) {
            sOP = ">";
        }
        if (OP == 6) {
            sOP = "=";
        }


        if (leftExp != null) leftExp.PrintMe();
        if (rightExp != null) rightExp.PrintMe();


        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("BINOP(%s)", sOP));

        if (leftExp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, leftExp.SerialNumber);
        if (rightExp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, rightExp.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t1 = null;
        TYPE t2 = null;

        if (leftExp != null) t1 = leftExp.SemantMe();
        if (rightExp != null) t2 = rightExp.SemantMe();

        if (t1 == null) {
            FunctionHelpers.printError(leftExp.myLine);
            return null;
        }
        if (t2 == null) {
            FunctionHelpers.printError(rightExp.myLine);
            return null;
        }
        if (t1.isFunction() || t2.isFunction()) {
            System.out.println("Error: invalid arguments in binop");
            FunctionHelpers.printError(this.myLine);
        }
        while (t1.isArray() && t2.isArray()) {
            t2 = ((TYPE_ARRAY) t2).type;
            t1 = ((TYPE_ARRAY) t1).type;

        }

        if (t1 == t2) {
            if ((t1 == TYPE_INT.getInstance()) && (t2 == TYPE_INT.getInstance())) {
                return TYPE_INT.getInstance();
            }
            if (t1 == TYPE_STRING.getInstance() && t2 == TYPE_STRING.getInstance() && OP == 0)
                return TYPE_STRING.getInstance();
            if (OP == 6) { 
                return TYPE_INT.getInstance();
            }
        } else if (OP == 6) { 
            if (t1.isArray() && t2.isArray()) {
                t1 = ((TYPE_ARRAY) t1).type;
                t2 = ((TYPE_ARRAY) t2).type;
            }
            if (t1 == TYPE_NIL.getInstance() && t2 == TYPE_NIL.getInstance()) { 
                return TYPE_INT.getInstance();
            }
            if (TYPE.eqByString(t1, "TYPE_NIL")) { 
                if (TYPE.eqByString(t2, "TYPE_ARRAY") || TYPE.eqByString(t2, "TYPE_CLASS")) {
                    return TYPE_INT.getInstance();
                }
            } else if (TYPE.eqByString(t2, "TYPE_NIL")) {
                if (TYPE.eqByString(t1, "TYPE_ARRAY") || TYPE.eqByString(t1, "TYPE_CLASS")) {
                    return TYPE_INT.getInstance();
                }
            } else if (FunctionHelpers.biDirectionalIsSonOf(t1, t2)) {
                return TYPE_INT.getInstance();
            }

        }
        System.out.println("Error: invalid parameters for binop");
        FunctionHelpers.printError(this.myLine);
        return null;
    }

    @Override
    public TYPE getExpType() {
        return null;
    }
}