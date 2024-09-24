package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_EXP;
import Helpers.*;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_INT;
import IR.*;
import TEMP.*;

public class AST_STMT_IF extends AST_STMT {
    public AST_EXP cond;
    public AST_STMT_LIST body;

    public AST_STMT_IF(AST_EXP cond, AST_STMT_LIST body) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("====================== stmt -> IF (exp) {stmtList} \n");

        this.cond = cond;
        this.body = body;
        left = cond;
        right = body;
    }

    public void PrintMe() {

        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "STMT IF");

        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {

        if (cond.SemantMe() != TYPE_INT.getInstance()) {
            System.out.format("ERROR condition in IF is not integer\n");
            FunctionHelpers.printError(cond.myLine);
        }


        SYMBOL_TABLE.getInstance().beginScope();

 
        body.SemantMe();


        SYMBOL_TABLE.getInstance().endScope();



        return null;
    }
    
	public TEMP IRme() {
		/*******************************/
		/* [1] Allocate 2 fresh labels */
		/*******************************/
		String label_false = IRcommand.getFreshLabel("false");
		String label_true = IRcommand.getFreshLabel("true");
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node1;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node2;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node3;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE root;
	  node3 = FunctionHelpers.currNode;
	  root = FunctionHelpers.graph.root;
	  node1 = FunctionHelpers.graph.new DATA_FLOW_NODE(label_false, node3);
	  node2 = FunctionHelpers.graph.new DATA_FLOW_NODE(label_true, node3);
	  FunctionHelpers.graph.connect(node2, node1);

		TEMP cond_temp = cond.IRme();

		/******************************************/
		/* [4] Jump conditionally to the loop end */
		/******************************************/
		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Jump_If_Eq_To_Zero(cond_temp,label_false));		
    IR.
		getInstance().
		Add_IRcommand(new IRcommand_Label(label_true));
		FunctionHelpers.graph.checkNode(node2);
		FunctionHelpers.currNode = node2;
		/*******************/
		/* [5] body.IRme() */
		/*******************/
		body.IRme();

		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Label(label_false));
    FunctionHelpers.graph.checkNode(node1);
    FunctionHelpers.currNode = node1;
		
		/*******************/
		/* [8] return null */
		/*******************/
		return null;
	}
}
