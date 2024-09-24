package AST;

import TEMP.*;
import IR.*;
import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_EXP;
import Helpers.*;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_INT;

public class AST_STMT_WHILE extends AST_STMT {
    public AST_EXP cond;
    public AST_STMT_LIST body;

    public AST_STMT_WHILE(AST_EXP cond, AST_STMT_LIST body) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.print("====================== stmt -> WHILE (exp) {stmtList} \n");

        this.cond = cond;
        this.body = body;
        right = body;
        left = cond;
    }

    public void PrintMe() {

        if (cond != null) cond.PrintMe();
        if (body != null) body.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "STMT WHILE");
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, cond.SerialNumber);
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, body.SerialNumber);
    }

    public TYPE SemantMe() {
        if (cond.SemantMe() != TYPE_INT.getInstance()) {
            System.out.format("ERROR condition inside WHILE is not integer\n");
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
		String label_end   = IRcommand.getFreshLabel("end");
		String label_start = IRcommand.getFreshLabel("start");
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node1;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node2;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE node3 = FunctionHelpers.currNode;
	  DATA_FLOW_GRAPH.DATA_FLOW_NODE root = FunctionHelpers.graph.root;
	  node1 = FunctionHelpers.graph.new DATA_FLOW_NODE(label_end, node3);
	  node2 = FunctionHelpers.graph.new DATA_FLOW_NODE(label_start, node3);
	  FunctionHelpers.graph.connect(node2, node1);
		/*********************************/
		/* [2] entry label for the while */
		/*********************************/
		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Label(label_start));
		FunctionHelpers.graph.checkNode(node2);
		FunctionHelpers.currNode = node2;
		

		/********************/
		/* [3] cond.IRme(); */
		/********************/
		TEMP cond_temp = cond.IRme();

		/******************************************/
		/* [4] Jump conditionally to the loop end */
		/******************************************/
		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Jump_If_Eq_To_Zero(cond_temp,label_end));		

		/*******************/
		/* [5] body.IRme() */
		/*******************/
		body.IRme();

		/******************************/
		/* [6] Jump to the loop entry */
		/******************************/
		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Jump_Label(label_start));		

		/**********************/
		/* [7] Loop end label */
		/**********************/
		IR.
		getInstance().
		Add_IRcommand(new IRcommand_Label(label_end));
		FunctionHelpers.graph.checkNode(node1);
		FunctionHelpers.currNode = node1;
		/*******************/
		/* [8] return null */
		/*******************/
		return null;
	}
}
