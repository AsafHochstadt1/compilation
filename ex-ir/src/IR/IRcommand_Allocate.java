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

public class IRcommand_Allocate extends IRcommand
{
	String var_name;
	
	public IRcommand_Allocate(String var_name)
	{
		this.var_name = var_name;
		check_vars();
	}
	@Override
	public void printIRCommand(){
	  System.out.print("Allocate var ");
	  System.out.print(this.var_name);
		System.out.print("\n");
	}
	
	@Override
		public void check_vars(){
		    FunctionHelpers.currNode.allocated.add(this.var_name);
		}
}
