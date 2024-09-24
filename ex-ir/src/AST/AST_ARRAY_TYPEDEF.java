package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_TYPE;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_ARRAY;

public class AST_ARRAY_TYPEDEF extends AST_DEC {
    String name;
    AST_TYPE type;

    public AST_ARRAY_TYPEDEF(String name, AST_TYPE type) {

        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== arrayTypeDef -> ARRAY ID(%s) = TYPE(%s)[]\n", name,type.type);

        this.name = name;
        this.type = type;
    }

    public void PrintMe() {
        if (type != null) type.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Array Def %s", name));
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);

    }

    @Override
    public TYPE_ARRAY SemantMe()
    {
        TYPE t = type.SemantMe(); 
        if (t == null) {
            FunctionHelpers.printError(this.myLine);
            return null;
        }
        TYPE_ARRAY newType = new TYPE_ARRAY(t, this.name);
        SYMBOL_TABLE.getInstance().enter(this.name, newType);
        return newType;
    }
}