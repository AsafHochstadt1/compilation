package AST;

import AST.AST_DEC;
import TYPES.TYPE;

public class AST_C_FIELD extends AST_Node {
    public AST_DEC dec;

    public AST_C_FIELD(AST_DEC dec) {

        System.out.println("====================== cField -> varDec | funcDec ");

        SerialNumber = AST_Node_Serial_Number.getFresh();
        this.dec = dec;
    }

    public void PrintMe() {


        if (dec != null) dec.PrintMe();


        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "CFIELD");


        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, dec.SerialNumber);
    }


    @Override
    public TYPE SemantMe() {
        return dec.SemantMe();
    }
}
