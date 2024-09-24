package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_EXP;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_INT;

public class AST_STMT_WHILE extends AST_STMT {
    public AST_EXP cond;
    public AST_STMT_LIST body;

    public AST_STMT_WHILE(AST_EXP cond, AST_STMT_LIST body) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== stmt -> WHILE (exp) {stmtList} \n");

        this.cond = cond;
        this.body = body;
        right = body;
        left = cond;
    }

    public void PrintMe() {

        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "STMT WHILE");
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {
        if (cond.SemantMe() != TYPE_INT.getInstance()) {
            System.out.format("ERROR condition inside WHILE is not integer\n");
            FunctionHelpers.printError(cond.myLine);
        }

        SYMBOL_TABLE.getInstance().beginScope();

        body.SemantMe();

        SYMBOL_TABLE.getInstance().endScope();

        return null;
    }
}