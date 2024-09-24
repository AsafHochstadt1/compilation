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

public class IRcommand_Load extends IRcommand
{
	TEMP dst;
	String var_name;
	
	public IRcommand_Load(TEMP dst,String var_name)
	{
		this.dst      = dst;
		this.var_name = var_name;
		check_vars();
	}
	
			@Override
	public void printIRCommand(){
	  System.out.print("LOAD ");
	  System.out.print(this.var_name);
	  if (this.dst != null){
	  System.out.print(" T");
	  System.out.print(this.dst.getSerialNumber());}
	  else {System.out.print(" null");}
		System.out.print("\n");
	}
	
	@Override
	public void check_vars(){
	    int flag = 0;
	    for (int i = 0; i < FunctionHelpers.currNode.allocated.size(); i++) {
          if (FunctionHelpers.currNode.allocated.get(i).equals(this.var_name)){
              flag = 1;
              }
          }
      if (flag == 0){
                for (int i = 0; i < FunctionHelpers.vars_ubi.size(); i++) {
              if (FunctionHelpers.vars_ubi.get(i).equals(this.var_name)){
                  flag = 1;
              }
          }
          if (flag == 0){
              FunctionHelpers.vars_ubi.add(this.var_name);
              System.out.print(this.var_name + " added to ubi\n");
        System.out.print(FunctionHelpers.vars_ubi + "\n");}
    }
	}
	
	}

