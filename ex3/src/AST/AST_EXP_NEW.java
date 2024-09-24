package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_TYPE;
import Helpers.FunctionHelpers;
import TYPES.TYPE;
import TYPES.TYPE_ARRAY;
import TYPES.TYPE_INT;
import TYPES.TYPE_NIL;

public class AST_EXP_NEW extends AST_EXP {
    public AST_TYPE type;
    public AST_EXP exp;

    public AST_EXP_NEW(AST_TYPE type, AST_EXP exp) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (exp != null) System.out.format("====================== exp -> NEW type(%s) [exp]\n", type.type);
        else System.out.format("====================== exp -> NEW type(%s) \n", type.type);


        this.type = type;
        this.exp = exp;
        left = type;
        right = exp;
    }

    public void PrintMe() {
        if (type != null) type.PrintMe();
        if (exp != null) exp.PrintMe();



        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("new Exp"));
        if (type != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public TYPE SemantMe() {
        if (exp != null) { 
            TYPE expType = exp.SemantMe();
            if (expType != TYPE_INT.getInstance()) { 
                System.out.format("ERROR: creating an array with non-integer size\n");
                FunctionHelpers.printError(myLine);
                return null;
            }
            if (exp.isConstantInt()) {
                if (exp.getValue() <= 0) {
                    System.out.format("ERROR: allocating array size with constant size expression must be greater than 0\n");
                    FunctionHelpers.printError(myLine);
                }
            }

            TYPE arrT = type.SemantMe();
            if (arrT == null) {
                System.out.format("ERROR: unknown object %s\n", type);
                FunctionHelpers.printError(myLine);
            }
            return new TYPE_ARRAY(arrT, null);
        }
        TYPE t = type.SemantMe();
        if (t == null) {
            System.out.format("ERROR: unknown object %s\n", type);
            FunctionHelpers.printError(myLine);
        }
        if (FunctionHelpers.isIntOrString(t) || t == TYPE_NIL.getInstance()) {
            System.out.format("Error: cannot initialize new primitive type\n");
            FunctionHelpers.printError(myLine);
        }
        if (t.isArray() && exp == null) {
            System.out.format("Error: cannot initialize new array without the size\n");
            FunctionHelpers.printError(myLine);
        }
        return t;

    }

    @Override
    public TYPE getExpType() {

        return null;
    }
}