package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_VAR;
import TYPES.TYPE;

public class AST_EXP_VAR extends AST_EXP {
    public AST_VAR var;

    public AST_EXP_VAR(AST_VAR var) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("====================== exp -> var\n");
        this.var = var;
    }

    public void PrintMe() {


        if (var != null) var.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "exp var");
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);

    }

    public TYPE SemantMe() {
        return var.SemantMe();
    }

    /*REMOVE TODO*/
    @Override
    public TYPE getExpType() {
        return null;
    }
}