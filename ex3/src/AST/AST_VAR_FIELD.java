package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import Helpers.FunctionHelpers;
import TYPES.*;

public class AST_VAR_FIELD extends AST_VAR {
    public AST_VAR var;
    public String fieldName;

    public AST_VAR_FIELD(AST_VAR var, String fieldName) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== var -> var.ID( %s )\n", fieldName);

        this.var = var;
        this.fieldName = fieldName;
        right = var;
    }

    public void PrintMe() {

        if (var != null) var.PrintMe();



        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("FIELD .%s", fieldName));


        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
    }

    public TYPE SemantMe() {
        TYPE t = null;
        TYPE_CLASS tc = null;

        if (var != null) t = var.SemantMe();

        if (t.isArray())
            t = ((TYPE_ARRAY) t).type;
        if (!(t.isClass())) {
            System.out.format("ERROR access %s field of a non-class variable\n", fieldName);
            FunctionHelpers.printError(this.myLine);
        } else {
            tc = (TYPE_CLASS) t;
        }

        for (TYPE_LIST it = tc.data_members; it != null; it = it.tail) {
            TYPE_CLASS_VAR_DEC dec = (TYPE_CLASS_VAR_DEC) it.head;
            if (dec.name.equals(fieldName)) {
                return ((TYPE_CLASS_VAR_DEC) it.head).t;
            }
        }

        System.out.format("ERROR field %s does not exist in class\n", fieldName);
        FunctionHelpers.printError(this.myLine);
        return null;
    }


    @Override
    public String getName() {
        return fieldName;
    }
}
