package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_STMT_LIST;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_FUNCTION;

public class AST_DEC_FUNC extends AST_DEC {


    public AST_STMT_LIST stmtList;
    public AST_FUNC_SIG sig; 
    public static TYPE func_type = null; 
    public AST_DEC_FUNC(AST_FUNC_SIG sig, AST_STMT_LIST list) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== funcDec -> sig stmtList\n");

        this.stmtList = list;
        this.sig = sig;
        left = sig;
        right = list;
    }

    public void PrintMe() {

        if (sig != null) sig.PrintMe();
        if (stmtList != null) stmtList.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Function Dec"));
        if (sig != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, sig.SerialNumber);
        if (stmtList != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, stmtList.SerialNumber);

    }


    public TYPE SemantMe() {

        SYMBOL_TABLE.getInstance().beginScope();
        func_type = sig.type.SemantMe(); 
        TYPE_FUNCTION newFuncDec = (TYPE_FUNCTION) sig.SemantMe(); 

        stmtList.SemantMe();

        SYMBOL_TABLE.getInstance().endScope();

        SYMBOL_TABLE.getInstance().enter(sig.name, newFuncDec);

        return newFuncDec;
    }


    public TYPE cSemantMe(String name) {

        SYMBOL_TABLE.getInstance().beginScope();

        TYPE_FUNCTION newFuncDec;
        func_type = sig.type.SemantMe();
        newFuncDec = (TYPE_FUNCTION) sig.cSemantMe(name);

        stmtList.SemantMe();

        SYMBOL_TABLE.getInstance().endScope();

        return newFuncDec;

    }


}