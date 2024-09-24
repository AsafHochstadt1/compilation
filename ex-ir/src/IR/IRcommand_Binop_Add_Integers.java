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

public class IRcommand_Binop_Add_Integers extends IRcommand
{
	public TEMP t1;
	public TEMP t2;
	public TEMP dst;
	
	public IRcommand_Binop_Add_Integers(TEMP dst,TEMP t1,TEMP t2)
	{
		this.dst = dst;
		this.t1 = t1;
		this.t2 = t2;
	}
	
		@Override
	public void printIRCommand(){
	  if (this.dst != null){
	    System.out.print("ADD T");
	    System.out.print(this.dst.getSerialNumber());}
	  else{System.out.print("ADD null");}
	  if (this.t1 != null){
	    System.out.print(" T");
	    System.out.print(this.t1.getSerialNumber());}
	  else {System.out.print(" null");}
	  if (this.t2 != null){
	    System.out.print(" T");
	    System.out.print(this.t2.getSerialNumber());}
	  else {System.out.print(" null");}  
		System.out.print("\n");
	}
}
