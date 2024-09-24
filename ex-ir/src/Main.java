import AST.AST_GRAPHVIZ;
import AST.AST_PROGRAM;
import Helpers.*;

import java.io.FileReader;
import java.util.function.Function;
import java.io.*;
import java.io.PrintWriter;
import java_cup.runtime.Symbol;
import AST.*;
import IR.*;
import TEMP.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main
{
  
	static public void main(String argv[])
	{
		Lexer l;
		Parser p;
		Symbol s;
		AST_PROGRAM AST;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		ArrayList<String> vars_allocated;
	  ArrayList<String> vars_ubi;
	  //ArrayList<String> vars_assigned;
	  ArrayList<ASSIGNED_VAR> assigned;
		try
		{
			/********************************/
			/* [1] Initialize a file reader */
			/********************************/
			file_reader = new FileReader(inputFilename);

			/********************************/
			/* [2] Initialize a file writer */
			/********************************/
			file_writer = new PrintWriter(outputFilename);
			FunctionHelpers.setFileWriter(file_writer);
			vars_allocated = new ArrayList<String>();
			vars_ubi = new ArrayList<String>();
			//vars_assigned = new ArrayList<String>();
			assigned = new ArrayList<ASSIGNED_VAR>();
			FunctionHelpers.setArrays(vars_ubi);
			FunctionHelpers.graph = new DATA_FLOW_GRAPH();
			FunctionHelpers.currNode = FunctionHelpers.graph.root;
			
			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(file_reader);
			
			/*******************************/
			/* [4] Initialize a new parser */
			/*******************************/
			p = new Parser(l, file_writer);

			/***********************************/
			/* [5] 3 ... 2 ... 1 ... Parse !!! */
			/***********************************/
			AST = (AST_PROGRAM) p.parse().value;
			

			/**************************/
			/* [7] Semant the AST ... */
			/**************************/
			/*process the AST to remove useless nodes and things*/
            		FunctionHelpers.reduceDecs(AST);
            		FunctionHelpers.organizeClassDecsInAst(AST);
            		/*TODO add global functions print thingy*/
            		/*semantic analysis*/
           		AST.SemantMe();

			/**********************/
			/* [8] IR the AST ... */
			/**********************/
			AST.IRme();
			if (FunctionHelpers.vars_ubi.size() == 0){
			    file_writer.write("OK!\n");}
			else {
			    Collections.sort(FunctionHelpers.vars_ubi);
			    for (int i = 0; i <FunctionHelpers.vars_ubi.size(); i++){
			      file_writer.write( FunctionHelpers.vars_ubi.get(i)+"\n");}
			
			}
			
			/**************************/
			/* [12] Close output file */
			/**************************/
			file_writer.close();
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}


