package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_VAR;
import TYPES.TYPE;
import TEMP.*;
import IR.*;
import Helpers.*;

public class AST_EXP_VAR extends AST_EXP {
    public AST_VAR var;

    public AST_EXP_VAR(AST_VAR var) {
        SerialNumber = AST_Node_Serial_Number.getFresh();
        System.out.print("====================== exp -> var\n");
        this.var = var;
    }
    
    @Override
    public void print(){
      System.out.print(this.var.getName() + " ");}

    public void PrintMe() {


        if (var != null) var.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, "exp var");
        AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);

    }

    public TYPE SemantMe() {
        return var.SemantMe();
    }

    /*REMOVE TODO*/
    @Override
    public TYPE getExpType() {
        return null;
    }
    
    @Override
    public TEMP IRme(){
      TEMP tmp = this.var.IRme();
      int flag = 0;
      String name = ((AST_VAR_SIMPLE) this.var).name;
      if (tmp == null || tmp.getSerialNumber() == 100){
          for (int i =0; i < FunctionHelpers.vars_ubi.size(); i++){
              if (FunctionHelpers.vars_ubi.get(i).equals(name)){
                  flag = 1;
                  break;
                  }
                }
              if (flag == 0){
                    FunctionHelpers.vars_ubi.add(name);
                    }
      }
      return tmp;
	}
	
}

