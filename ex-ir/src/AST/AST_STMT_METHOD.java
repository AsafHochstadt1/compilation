package AST;

import AST.AST_GRAPHVIZ;
import AST.AST_Node_Serial_Number;
import AST.AST_EXP_LIST;
import AST.AST_EXP_METHOD;
import AST.AST_VAR;
import Helpers.*;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_CLASS;
import TYPES.TYPE_FUNCTION;
import TYPES.TYPE_LIST;
import IR.*;
import TEMP.*;

public class AST_STMT_METHOD extends AST_STMT {

    public String id;
    public AST_VAR var;
    public AST_EXP_LIST args;

    public AST_STMT_METHOD(AST_VAR var, String id, AST_EXP_LIST args) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        if ((var != null) && (args != null))
            System.out.printf("====================== stmt -> var. %s (expList)\n", id);
        else if (var != null)
            System.out.printf("====================== stmt -> var. %s (expList)\n", id);
        else if (args != null)
            System.out.printf("====================== stmt -> %s (expList)\n", id);
        else
            System.out.printf("====================== stmt -> %s ()\n", id);

        this.var = var;
        this.id = id;
        this.args = args;

        left = var;
        right = args;

    }

    public void PrintMe() {
        if (var != null) var.PrintMe();
        if (args != null) args.PrintMe();

        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("call for Method: NAME(%s)", id));
        if (var != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, var.SerialNumber);
        if (args != null) AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, args.SerialNumber);
    }

    @Override
    public TYPE SemantMe() {
        TYPE_FUNCTION func = null;
        if (var != null) {
            TYPE c = var.SemantMe();
            if (!c.isClass()) {
                System.out.format("Error: calling a method from non-class\n");
                FunctionHelpers.printError(this.myLine);
                return null;
            }
            TYPE_CLASS classType = (TYPE_CLASS) c;

            /*compare function args*/
            for (TYPE_LIST runner = classType.function_list; runner != null; runner = runner.tail) {

                TYPE_FUNCTION runnerF = (TYPE_FUNCTION) runner.head;
                if (runnerF.name.equals(this.id))
                    func = (TYPE_FUNCTION) runner.head;
            }

            if (func == null) {
                System.out.format("ERROR: no such func %s in class %s" ,id, classType.name);
                FunctionHelpers.printError(this.myLine);
                return null;
            }
        } else {
            func = (TYPE_FUNCTION) SYMBOL_TABLE.getInstance().find(this.id);
            if (func == null) {
                System.out.format("ERROR: no such func %s",id);
                FunctionHelpers.printError(this.myLine);
                return null;
            }
        }

        if (args == null) {
            if (func.arguments != null) {
                System.out.format("Error: not enough arguments sent to function\n");
                FunctionHelpers.printError(myLine);
            }
        }
        if (func.arguments == null) {
            if (args != null) {
                System.out.format("Error: too many arguments sent to function\n");
                FunctionHelpers.printError(myLine);
            }
        }
        if (!(func.arguments == null && args == null))
            AST_EXP_METHOD.funcCallSemanter(args, func);
        return func.returnType;
    }
    
    public TEMP IRme(){
          TEMP tmp = this.args.IRme();
          AST_EXP_VAR argVar = null;
          AST_VAR_SIMPLE simp = null;
          int flag = 0;
          if (tmp == null || tmp.getSerialNumber() == 100){
            argVar = (AST_EXP_VAR) args.head;
            simp = (AST_VAR_SIMPLE) argVar.var;
            for (int i =0; i < FunctionHelpers.vars_ubi.size(); i++){
              if (FunctionHelpers.vars_ubi.get(i).equals(simp.name)){
                flag = 1;
                break; }
                }
            if (flag == 0){
            FunctionHelpers.vars_ubi.add(simp.name);
              }
            }
          return null;
       }
  }

