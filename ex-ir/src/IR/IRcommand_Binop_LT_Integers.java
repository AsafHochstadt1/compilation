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

public class IRcommand_Binop_LT_Integers extends IRcommand
{
	public TEMP t1;
	public TEMP t2;
	public TEMP dst;

	public IRcommand_Binop_LT_Integers(TEMP dst,TEMP t1,TEMP t2)
	{
		this.dst = dst;
		this.t1 = t1;
		this.t2 = t2;
	}
	
			@Override
	public void printIRCommand(){
	  System.out.print("LT ");
	  if (this.dst != null){
	  System.out.print(this.dst);}
	  else {System.out.print("null");}
	  System.out.print(" ");
	  if (this.t1 != null){
	  System.out.print(this.t1);}
	  else {System.out.print("null");}
	  System.out.print(" ");
	  if (this.t2 != null){
	  System.out.print(this.t2);}
	  else {System.out.print("null");}
		System.out.print("\n");
	}
}
