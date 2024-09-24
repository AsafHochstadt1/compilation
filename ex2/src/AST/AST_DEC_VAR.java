package AST;

public class AST_DEC_VAR extends AST_DEC {
    AST_TYPE typeNode;
    String id;
    AST_EXP exp;

    public AST_DEC_VAR(AST_TYPE typeNode, String id, AST_EXP exp) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        if (exp != null){
            System.out.format("====================== varDec -> TYPE ID(%s):ASSIGN EXP SEMICOLON\n", id);
        }
        else{
            System.out.format("====================== varDec -> TYPE ID(%s) SEMICOLON\n", id);
        }

        this.typeNode = typeNode;
        this.id = id;
        this.exp = exp;
    }

    public void PrintMe() {
        if (typeNode!= null) typeNode.PrintMe();
        System.out.format("AST_DEC_VAR ID(%s)\n", id);
        if (exp != null) exp.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Var Declaration: NAME(%s)", id));
        if (typeNode != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, typeNode.SerialNumber);
        if (exp != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, exp.SerialNumber);
    }
}
