package AST;

import AST.*;
import Helpers.FunctionHelpers;
import SYMBOL_TABLE.SYMBOL_TABLE;
import TYPES.TYPE;
import TYPES.TYPE_CLASS;
import TYPES.TYPE_FUNCTION;
import TYPES.TYPE_LIST;

public class AST_FUNC_SIG extends AST_Node {

    public AST_TYPE type;
    public String name;
    public AST_ID_LIST idList;

    public AST_FUNC_SIG(AST_TYPE type, String name, AST_ID_LIST idList) {
        SerialNumber = AST_Node_Serial_Number.getFresh();

        System.out.format("====================== funcDec -> TYPE( %s ) NAME(%s)\n", type.type, name);

        this.type = type;
        this.name = name;
        this.idList = idList;
        left = idList;

    }


    public void PrintMe() {

        if (type != null) type.PrintMe();
        if (idList != null) idList.PrintMe();
        AST_GRAPHVIZ.getInstance().logNode(SerialNumber, String.format("Function Signature %s", name));
        if (type != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, type.SerialNumber);
        if (idList != null)
            AST_GRAPHVIZ.getInstance().logEdge(SerialNumber, idList.SerialNumber);

    }

    @Override
    public TYPE SemantMe() {

        TYPE typeOfCurrNode;
        AST_TYPE astType;
        TYPE_LIST type_list = null;

        TYPE returnType = type.SemantMe();

        if (SYMBOL_TABLE.getInstance().find(name) != null) {
            System.out.format("ERROR function %s has already been declared\n", name);
            FunctionHelpers.printError(myLine);
        }

        if (returnType == null) {
            System.out.format("ERROR non existing return type for function %s\n", name);
            FunctionHelpers.printError(myLine);
        }

        if (idList != null) {
            type_list = new TYPE_LIST(null, null);
            for (AST_Node node : idList) {
                AST_DEC_VAR var = (AST_DEC_VAR) node;
                astType = var.typeNode;
                typeOfCurrNode = astType.SemantMe(); 
                type_list.add(typeOfCurrNode);
                SYMBOL_TABLE.getInstance().enter(var.id, typeOfCurrNode);
            }
        }


        TYPE_FUNCTION newFuncDec = new TYPE_FUNCTION(returnType, this.name, type_list);

        SYMBOL_TABLE.getInstance().enter(this.name, newFuncDec);

        return newFuncDec;

    }

    public TYPE cSemantMe(String className) {

        TYPE typeOfCurrNode;
        AST_TYPE astType;
        TYPE_LIST type_list = null;

        TYPE returnType = type.SemantMe();

        if (SYMBOL_TABLE.getInstance().find(name) != null) {
            System.out.format("ERROR function of name %s has already been declared\n", name);
            FunctionHelpers.printError(myLine);
        }

        if (returnType == null) {
            System.out.format("ERROR non existing return type for function %s\n", name);
            FunctionHelpers.printError(myLine);
        }

        if (idList != null) {
            type_list = new TYPE_LIST(null, null);

            for (AST_Node node : idList) {
                AST_DEC_VAR var = (AST_DEC_VAR) node;
                astType = var.typeNode;
                typeOfCurrNode = astType.SemantMe(); 
                type_list.add(typeOfCurrNode);
                SYMBOL_TABLE.getInstance().enter(var.id, typeOfCurrNode);
            }
        }

        TYPE_CLASS containingClass = (TYPE_CLASS) SYMBOL_TABLE.getInstance().find(className);

        if (containingClass.localFuncs.findInList(this.name) != null) {
            System.out.format("Error same function %s declaration twice\n",name);
            FunctionHelpers.printError(this.myLine);
        }


        TYPE_FUNCTION overloadedFunc = (TYPE_FUNCTION) containingClass.function_list.findInList(this.name);
        if (overloadedFunc != null) { 
            if (overloadedFunc.returnType != returnType) {
                System.out.format("ERROR function overloading %s different return types\n",name);
                FunctionHelpers.printError(myLine);
            } else {
                if (type_list == null) {
                    if (overloadedFunc.arguments != null) {
                        System.out.format("ERROR function overloading %s different args\n", name);
                        FunctionHelpers.printError(myLine);
                    }
                } else if (!type_list.compareFuncArgsByType(containingClass.function_list)) {
                    System.out.format("ERROR function overloading %s different args\n",name);
                    FunctionHelpers.printError(myLine);
                }
            }
        }



        TYPE_FUNCTION newFuncDec = new TYPE_FUNCTION(returnType, this.name, type_list);

        SYMBOL_TABLE.getInstance().enter(name, newFuncDec);
        containingClass.localFuncs.add(newFuncDec);

        return newFuncDec;

    }
}