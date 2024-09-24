package AST;

public class AST_C_FIELD extends AST_Node {
    public AST_DEC dec;
  
    public AST_C_FIELD(AST_DEC dec) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.println("cField -> varDec | funcDec\n");
        this.dec = dec;
    }

    public void PrintMe() {
        System.out.print("AST C_FIELD\n");
        if (dec != null) dec.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "C_FIELD");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, dec.SerialNumber);
    }
}
