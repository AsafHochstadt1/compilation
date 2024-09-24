package AST;

import AST.AST_C_FIELD_LIST;
import AST.AST_FUNC_LIST;
import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_VAR_LIST;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_CLASS;
import TYPES.TYPE_LIST;

public class AST_DEC_CLASS extends AST_DEC {

    public AST_CLASS_SIG sig;
    public AST_C_FIELD_LIST cfieldList;
    public AST_FUNC_LIST funcList;
    public AST_VAR_LIST varList;


    public AST_DEC_CLASS(AST_CLASS_SIG sig, AST_C_FIELD_LIST cfieldList) {

        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== decClass -> SIG body\n");

        this.sig = sig;
        this.cfieldList = cfieldList;
        right = cfieldList;
    }


    public void PrintMe() {

        if (cfieldList != null) cfieldList.PrintMe();

        if (this.sig.ext != null)
            AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("ClassDec %s EXTENDS %s", this.sig.name, this.sig.ext));
        else AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("ClassDec %s", this.sig.name));
        if (cfieldList != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cfieldList.SerialNumber);

    }

    public TYPE SemantMe() {

        TYPE_CLASS t = sig.SemantMe();

      
        SYMBOL_TABLE.getInstance().beginScope();

        TYPE_LIST varTypeList = null;
        TYPE_LIST funcTypeList = null;
        if (this.varList != null) 
            varTypeList = varList.cSemantMe(sig.name);
        if (this.funcList != null) 
            funcTypeList = funcList.cSemantMe(sig.name);

        t.data_members.addAll(varTypeList);
        t.function_list.addAll(funcTypeList);

        SYMBOL_TABLE.getInstance().endScope();

        SYMBOL_TABLE.getInstance().enter(sig.name, t);

        return null;
    }

    public String getName() {
        return this.sig.name;
    }

}