package AST;

public class AST_STMT_METHOD extends AST_STMT {
    public String id;
    public AST_VAR var;
    public AST_EXP_LIST args;

    public AST_STMT_METHOD(AST_VAR var, String id, AST_EXP_LIST args) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if ((args != null) && (var != null)){
            System.out.printf("====================== stmt -> var. %s LPAREN exps RPAREN\n", id);
        }
        else if ((args == null) && (var != null)){
            System.out.printf("====================== stmt -> var. %s LPAREN exps RPAREN\n", id);
        }
        else if ((args != null) && (var == null)){
            System.out.printf("====================== stmt -> %s LPAREN exps RPAREN\n", id);
        }
        else{
            System.out.printf("stmt -> %s LPAREN RPAREN\n", id);
        }

        this.args = args;
        this.var = var;
        this.id = id;
    }

    public void PrintMe() {
        System.out.format("AST STMT METHOD(%s)\n", id);
        if (var != null) var.PrintMe();
        if (args != null) args.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("call for Method: NAME(%s)", id));
        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (args != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, args.SerialNumber);
    }
}
