/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/
import java.util.*;
/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import Helpers.*;

public class IRcommand_Label extends IRcommand
{
	String label_name;
	
	public IRcommand_Label(String label_name)
	{
		this.label_name = label_name;
		int flag = 0;
		DATA_FLOW_GRAPH.DATA_FLOW_NODE newNode = null;
		ArrayList<DATA_FLOW_GRAPH.DATA_FLOW_NODE> forward = FunctionHelpers.graph.root.forward;
		for (int i = 0; i < forward.size(); i++){
		    if (forward.get(i).label_name.equals(this.label_name)){
		        flag = 1;
		        break;
		        }
		    }
		    if (flag == 0){
		      newNode = FunctionHelpers.graph.new DATA_FLOW_NODE(this.label_name, FunctionHelpers.currNode);
		      newNode.assigned = FunctionHelpers.currNode.assigned;
		      newNode.allocated = FunctionHelpers.currNode.allocated;
		      FunctionHelpers.currNode = newNode;
		      
		    }
		
	}
	
				@Override
	public void printIRCommand(){
	  System.out.print(this.label_name);
	  System.out.print(":");
		System.out.print("\n");
	}
}
