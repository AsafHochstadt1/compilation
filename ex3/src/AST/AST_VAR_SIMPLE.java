package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;

public class AST_VAR_SIMPLE extends AST_VAR {
    public String name;

    public AST_VAR_SIMPLE(String name) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== var -> ID( %s )\n", name);

        this.name = name;
    }

    public void PrintMe() {

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("VAR(%s)", name));
    }

    public TYPE SemantMe() {
        if (SYMBOL_TABLE.getInstance().find(name) == null) {
            System.out.format("ERROR: variable %s is not defined\n", name);
            FunctionHelpers.printError(myLine);
        }
        TYPE t = SYMBOL_TABLE.getInstance().find(name);
        if (t.name.equals(this.name)) {
            System.out.println("Error: static reference");
            FunctionHelpers.printError(myLine);
        }
        return t;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
