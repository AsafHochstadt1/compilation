package AST;

public class AST_EXP_NEW extends AST_EXP {
    public AST_TYPE t;
    public AST_EXP exp;

    public AST_EXP_NEW(AST_TYPE t, AST_EXP exp) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (exp != null){
            System.out.format("====================== exp -> NEW type LBRACK exp RBRACK\n");
        }
        else{
            System.out.format("====================== exp -> NEW type\n");
        }

        this.t = t;
        this.exp = exp;
    }

    public void PrintMe() {
        System.out.format("AST EXP NEW\n");
        if (t != null) t.PrintMe();
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Exp New"));
        if (t != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, t.SerialNumber);
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }
}
