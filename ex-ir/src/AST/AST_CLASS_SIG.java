package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node;
import AST.AST_Node_Serial_Number;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_CLASS;

public class AST_CLASS_SIG extends AST_Node {

    public String name;
    public String ext;

    public AST_CLASS_SIG(String name, String ext) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if (ext != null) {
            System.out.format("====================== classSig -> ID(%s) extends ID(%s)\n", name, ext);
        } else {
            System.out.format("====================== classSig -> ID(%s)\n", name);
        }

        this.name = name;
        this.ext = ext;
    }

    public void PrintMe() {
        if (ext != null)
            AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("ClassDec %s EXTENDS %s", name, ext));
        else
            AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("ClassDec %s", name));
    }

    public TYPE_CLASS SemantMe() {
        TYPE extType;
        TYPE_CLASS fatherTypeClass = null;
        if (ext != null) {
            extType = SYMBOL_TABLE.getInstance().find(ext);
            if (extType == null) {
                System.out.format("ERROR extended class %s does not exist\n", ext);
                FunctionHelpers.printError(myLine);
            } else if (!extType.isClass()) {
                System.out.format("ERROR extended class %s isn't a class Type\n", ext);
                FunctionHelpers.printError(myLine);
            } else {
                fatherTypeClass = (TYPE_CLASS) extType;
            }
        }
        TYPE_CLASS currType = new TYPE_CLASS(name, fatherTypeClass, null);
        if (fatherTypeClass != null) {
            currType.data_members.addAll(fatherTypeClass.data_members);
            currType.function_list.addAll(fatherTypeClass.function_list);
        }


        SYMBOL_TABLE.getInstance().enter(name, currType);

        return currType;
    }

    public String getName() {
        return this.name;
    }
}