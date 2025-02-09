package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_DEC_FUNC;
import AST.AST_EXP;
import Helpers.FunctionHelpers;
import TYPES.TYPE;
import TYPES.TYPE_VOID;

public class AST_STMT_RETURN extends AST_STMT {
    public AST_EXP res;

    public AST_STMT_RETURN(AST_EXP res) {

        SerialNumber = AST_Node_Serial_Number.getFresh();
        if (res != null) System.out.print("====================== stmt -> RETURN exp;\n");
        if (res == null) System.out.print("====================== stmt -> RETURN;\n");

        this.res = res;
        right = res;

    }

    public void PrintMe() {
        if (res != null) res.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "RETURN Statement");
        if (res != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, res.SerialNumber);
    }

    @Override
    public TYPE SemantMe() {
        TYPE t;
        if (res != null) {
            t = res.SemantMe();
            if (FunctionHelpers.isSameOrInheritorOrValidNil(t, AST_DEC_FUNC.func_type)) {
                return t;
            }
        } else {
            /*return;*/
            if (AST_DEC_FUNC.func_type == TYPE_VOID.getInstance()) {
                return null;
            }
        }
        System.out.println("error incompatible error in function type");
        FunctionHelpers.printError(res.myLine);
        return null;
    }
}
