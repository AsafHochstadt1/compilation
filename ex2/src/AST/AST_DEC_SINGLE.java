package AST;

public class AST_DEC_SINGLE extends AST_DEC {
    public AST_DEC dec;
    public AST_DEC_SINGLE(AST_DEC dec) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("====================== dec ->  varDec | funcDec | classDec | arrayTypeDef\n");
        this.dec = dec;
    }

    public void PrintMe() {
        System.out.print("AST DEC SINGLE\n");
        if (dec != null) dec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "Single Declaration");
        if (dec != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, dec.SerialNumber);
    }
}
