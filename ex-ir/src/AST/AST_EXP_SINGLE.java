package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import TYPES.TYPE;
import TEMP.*;
import IR.*;
import Helpers.*;

public class AST_EXP_SINGLE extends AST_EXP {
    public AST_EXP exp;

    public AST_EXP_SINGLE(AST_EXP son) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== exp -> (exp)\n");

        this.exp = son;
    }

    public void PrintMe() {

        if (exp != null) exp.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("exp"));
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }

    public TYPE SemantMe() {
        return exp.SemantMe();
    }


    @Override
    public TYPE getExpType() {
        return exp.getExpType();
    }
    
    @Override
    public TEMP IRme(){
        return this.exp.IRme();
        }

}
