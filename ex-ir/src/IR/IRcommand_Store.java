/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/
import java.util.ArrayList;
/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import Helpers.*;

public class IRcommand_Store extends IRcommand
{
	String var_name;
	TEMP src;
	
	public IRcommand_Store(String var_name,TEMP src)
	{
		this.src      = src;
		this.var_name = var_name;
		check_vars();
	}
	
				@Override
	public void printIRCommand(){
	  System.out.print("STORE ");
	  System.out.print(this.var_name);
	  if (this.src != null){
	  System.out.print(" T");
	  System.out.print(this.src.getSerialNumber());}
	  else{System.out.print(" null");}
		System.out.print("\n");
	}
	
		@Override
	public void check_vars(){
	    int flag = 0;
	    int i = 0;
	    DATA_FLOW_GRAPH.DATA_FLOW_NODE node = FunctionHelpers.currNode;
	    if (this.src == null || this.src.getSerialNumber() == 100){
	        for (i = 0; i < FunctionHelpers.vars_ubi.size(); i++) {
              if (FunctionHelpers.vars_ubi.get(i).equals(this.var_name)){
                flag = 1;
                break;
                }
          }
          if (flag == 0){
	        FunctionHelpers.vars_ubi.add(this.var_name);
	        }
	        return;}
	    for (i = 0; i < node.allocated.size(); i++) {
          if (node.allocated.get(i).equals(this.var_name)){
              flag = 1;
              break;
              }
          }
      if (flag == 0){
          for (i = 0; i < FunctionHelpers.vars_ubi.size(); i++) {
              if (FunctionHelpers.vars_ubi.get(i).equals(this.var_name)){
                flag = 1;
                break;
                }
          }
          if (flag == 0){
              FunctionHelpers.vars_ubi.add(this.var_name);
              }
             }
        if (flag == 1) { 
          for (i = 0; i < node.assigned.size(); i++) {
              if (node.assigned.get(i).search(this.var_name) != null){
                if (node.assigned.get(i).temp.getSerialNumber() ==100){
                    for (i = 0; i < FunctionHelpers.vars_ubi.size(); i++) {
                  if (FunctionHelpers.vars_ubi.get(i).equals(this.var_name)){
                    flag = 0;
                    break;
                }
          }
                if (flag == 1){
                  FunctionHelpers.vars_ubi.add(this.var_name);
                  }
                  }
                else{
                  node.assigned.get(i).temp = this.src;
                  }
                flag = 0;
                break;
                }
                }
              }
            if (flag == 1){
              ASSIGNED_VAR temp = new ASSIGNED_VAR(this.var_name, this.src);
              node.assigned.add(temp);   
              }
	}
	
}


