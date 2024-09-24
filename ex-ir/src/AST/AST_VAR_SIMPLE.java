package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import Helpers.*;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TEMP.*;
import IR.*;

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
    
    @Override
    public TEMP IRme(){
        int flag = 0;
        DATA_FLOW_GRAPH.DATA_FLOW_NODE node = FunctionHelpers.currNode;
        ASSIGNED_VAR temp;
        if (node.assigned == null || node.assigned.size() == 0){
            temp = new ASSIGNED_VAR(this.name, FunctionHelpers.dummy);
            node.assigned.add(temp);
            return FunctionHelpers.dummy;
            }
	      for (int i = 0; i < node.assigned.size(); i++) {
          if (node.assigned.get(i).var_name.equals(this.name)){
              TEMP t = TEMP_FACTORY.getInstance().getFreshTEMP();
		          IR.getInstance().Add_IRcommand(new IRcommand_Load(t,name));
		          node.assigned.get(i).temp = t;
		          return t;
              }
             }
        temp = new ASSIGNED_VAR(this.name, FunctionHelpers.dummy);
        node.assigned.add(temp);
        return FunctionHelpers.dummy; 
        }

}
