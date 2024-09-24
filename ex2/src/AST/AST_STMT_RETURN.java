package AST;

public class AST_STMT_RETURN extends AST_STMT {
    public AST_EXP res;

    public AST_STMT_RETURN(AST_EXP res) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (res != null) System.out.print("====================== stmt -> RETURN exp SEMICOLON\n");
        if (res == null) System.out.print("====================== stmt -> RETURN SEMICOLOM\n");

        this.res = res;
    }

    public void PrintMe() {
        System.out.print("AST STMT RETURN\n");
        if (res != null) res.PrintMe();
      
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "STMT RETURN");
      
        if (res != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, res.SerialNumber);
    }
}
