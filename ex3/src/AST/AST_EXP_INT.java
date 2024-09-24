package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import TYPES.TYPE;
import TYPES.TYPE_INT;

public class AST_EXP_INT extends AST_EXP {
    public int value;

    public AST_EXP_INT(int value) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== exp -> INT( %d )\n", value);

        this.value = value;
    }

    public void PrintMe() {

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("INT(%d)", value));
    }

    public TYPE SemantMe() {
        return TYPE_INT.getInstance();
    }


    @Override
    public TYPE getExpType() {
        return null;
    }


    @Override
    public boolean isConstantInt() {
        return true;
    }

    public int getValue() {
        return value;
    }
}