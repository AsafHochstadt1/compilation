package AST;

import AST.AST_Node;
import TYPES.TYPE;

public abstract class AST_EXP extends AST_Node {
    public void PrintMe() {
        System.out.print("CANNOT PRINT AST_EXP");
    }

    public abstract TYPE getExpType();

    public boolean isConstantInt() {
        return false;
    }

    public int getValue() {
        return -1;
    }

}
