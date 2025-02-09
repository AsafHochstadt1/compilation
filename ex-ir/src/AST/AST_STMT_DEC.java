package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_DEC_VAR;
import TYPES.TYPE;

public class AST_STMT_DEC extends AST_STMT {
    public AST_DEC_VAR dec;

    public AST_STMT_DEC(AST_DEC_VAR dec) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== stmt -> varDec\n");
        this.dec = dec;
        right = dec;
    }

    public void PrintMe() {
        if (dec != null) dec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "Var Declaration");
        if (dec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, dec.SerialNumber);
    }

    public TYPE SemantMe() {
        return dec.SemantMe();
    }
}
