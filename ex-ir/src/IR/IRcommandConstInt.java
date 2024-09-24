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

public class IRcommandConstInt extends IRcommand
{
	TEMP t;
	int value;
	
	public IRcommandConstInt(TEMP t,int value)
	{
		this.t = t;
		this.value = value;
	}
	
			@Override
	public void printIRCommand(){
	  System.out.print("Assign T");
	  System.out.print(this.t.getSerialNumber());
	  System.out.print(" ");
	  System.out.print(this.value);
		System.out.print("\n");
	}
}
