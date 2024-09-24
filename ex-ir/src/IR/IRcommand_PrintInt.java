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
import java.util.ArrayList;

public class IRcommand_PrintInt extends IRcommand
{
	TEMP t;
	
	public IRcommand_PrintInt(TEMP t)
	{
		this.t = t;
	}
	
				@Override
	public void printIRCommand(){
	  if (this.t != null){
	  System.out.print("PrintInt T");
	  System.out.print(this.t.getSerialNumber());}
		System.out.print("\n");
	}
	
}

