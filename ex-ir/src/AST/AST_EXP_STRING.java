package AST;

import TYPES.*;
import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;

public class AST_EXP_STRING extends AST_EXP {
    public String value;

    public AST_EXP_STRING(String str) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.format("====================== exp -> String( %s )\n", str);
        this.value = str;
    }

    public void PrintMe() {

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("STRING(%s)", value.substring(1, value.length() - 1)));
    }

    @Override
    public TYPE SemantMe() {
        return TYPE_STRING.getInstance();
    }

    @Override
    public TYPE getExpType() {
        return null;
    }
}
