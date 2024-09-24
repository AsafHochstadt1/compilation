package AST;

public class AST_EXP_SINGLE extends AST_EXP {
    public AST_EXP exp;

    public AST_EXP_SINGLE(AST_EXP son) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("====================== exp -> (exp)\n");
        this.exp = son;
    }

    public void PrintMe() {
        System.out.print("AST EXP SINGLE\n");
        if (exp != null) exp.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                String.format("Single Expression"));
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }
}
