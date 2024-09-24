package AST;

import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_VOID;

public class AST_TYPE extends AST_Node {
    public String type; 
    public Boolean isID; 

    public AST_TYPE(String type,Boolean isID) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if(isID)
        {
            System.out.format("====================== type -> ID(%s)\n", type);
        }
        else
        {
            System.out.format("====================== type -> %s\n", type);
        }


        this.type = type;
        this.isID = isID;
    }

    public void PrintMe() {
        if(isID)
        {
            System.out.format("AST_TYPE ID(%s)\n", type);
        }
        else
        {
            System.out.format("AST_TYPE %s \n", type);
        }

        if(isID)
        {
            AST_GRAPHVIZ.getInstance().logNode(
                    SerialNumber,
                    String.format("TYPE ID(%s)", type));
        }
        else /*primitive type*/
        {
            AST_GRAPHVIZ.getInstance().logNode(
                    SerialNumber,
                    String.format("TYPE %s", type));
        }

    }

    @Override
    public TYPE SemantMe()
    {
        TYPE t;
        if(type.equals("void"))
            return TYPE_VOID.getInstance();
        t = SYMBOL_TABLE.getInstance().find(type);
        if (t == null)
        {
            System.out.format("ERROR non existing type %s\n",type);
            FunctionHelpers.printError(myLine);
        }
        return t;
    }


}
