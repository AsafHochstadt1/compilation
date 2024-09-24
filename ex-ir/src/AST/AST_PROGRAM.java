package AST;

import AST.AST_DEC_LIST;
import TYPES.TYPE;
import TEMP.*;
import IR.*;

public class AST_PROGRAM extends AST_Node {

    public AST_DEC_LIST decList;


    public AST_PROGRAM(AST_DEC_LIST decList) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== Program -> [decList]+\n");
        this.decList = decList;
    }

    public void PrintMe() {

        System.out.format("AST_PROGRAM\n");


        decList.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(
                SerialNumber,
                "PROGRAM");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, decList.SerialNumber);
    }

    @Override
    public TYPE SemantMe() {
        return this.decList.SemantMe();
    }
    
    public TEMP IRme()
	{
		this.decList.IRme();
		return null;			
	}
}
