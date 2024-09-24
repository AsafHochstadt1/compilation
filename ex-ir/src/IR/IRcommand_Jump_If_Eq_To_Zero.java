/***********/
/* PACKAGE */
/***********/
package IR;

/*******************/
/* GENERAL IMPORTS */
/*******************/

/*******************/
/* PROJECT IMPORTS */
/*******************/
import TEMP.*;
import Helpers.*;

public class IRcommand_Jump_If_Eq_To_Zero extends IRcommand
{
	TEMP t;
	String label_name;
	
	public IRcommand_Jump_If_Eq_To_Zero(TEMP t, String label_name)
	{
		this.t          = t;
		this.label_name = label_name;
	}
	
			@Override
	public void printIRCommand(){
	  System.out.print("Jump_If_Eq_To_Zero ");
	  if (this.t != null){
	  System.out.print("T " + this.t.getSerialNumber());}
	  else{System.out.print("null");}
	  System.out.print(" ");
	  System.out.print(this.label_name);
		System.out.print("\n");
	}
}
