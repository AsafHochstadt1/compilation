package Helpers;
import java.util.*;

import IR.*;
import TEMP.*;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.*;


public class ASSIGNED_VAR{
	public String var_name;
	public TEMP temp;
	
	public ASSIGNED_VAR(AST_VAR var, TEMP tmp){
		this.var_name = var.getName();
		this.temp = tmp;
		}	
	
	public ASSIGNED_VAR(String var_name, TEMP tmp){
		this.var_name = var_name;
		this.temp = tmp;
		}	
	
	
	public ASSIGNED_VAR search(String var){
		if (this.var_name.equals(var)){
			return this;}
		return null;
		
		}
		
	public void print_assigned(){
	
	  System.out.print(this.var_name + " , T");
	  System.out.print(this.temp.getSerialNumber() + " ");
	}
	}
