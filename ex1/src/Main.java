   
import java.io.*;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;
   
public class Main
{
	static public void main(String argv[])
	{
		Lexer l;
		Symbol s;
		FileReader file_reader;
		PrintWriter file_writer;
		String inputFilename = argv[0];
		String outputFilename = argv[1];
		String[] Tokens = {"EOF", "PLUS", "MINUS", "TIMES", "DIVIDE", "LPAREN", "RPAREN", "INT", "ID", "LBRACK", "RBRACK", "LBRACE", "RBRACE", "NIL", "COMMA", "DOT", "SEMICOLON", "TYPE_INT", "TYPE_VOID", "ASSIGN" ,"EQ", "LT", "GT", "ARRAY", "CLASS", "EXTENDS", "RETURN", "WHILE", "IF", "NEW", "STRING", "TYPE_STRING", "ERROR"};
		
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
			
			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(file_reader);

			/***********************/
			/* [4] Read next token */
			/***********************/
			s = l.next_token();

			/********************************/
			/* [5] Main reading tokens loop */
			/********************************/
			while (s.sym != TokenNames.EOF)
			{	
				/*********************/
				/* [7] Print to file */
				/*********************/
				if (Tokens[s.sym] == "ERROR"){
				PrintWriter file_writer_error = new PrintWriter(outputFilename);
				file_writer_error.print("ERROR");
				file_writer_error.print("\n");
				file_writer_error.close();
				System.exit(0);}
				file_writer.print(Tokens[s.sym]);
				if (s.value != null){
				file_writer.print("(");
				file_writer.print(s.value);
				file_writer.print(")");
				}
				file_writer.print("[");
				file_writer.print(l.getLine());
				file_writer.print(",");
				file_writer.print(l.getTokenStartPosition());
				file_writer.print("]");
				file_writer.print("\n");
				
				/***********************/
				/* [8] Read next token */
				/***********************/
				s = l.next_token();
			}
			
			/******************************/
			/* [9] Close lexer input file */
			/******************************/
			l.yyclose();

			/**************************/
			/* [10] Close output file */
			/**************************/
			file_writer.close();
    	}
			     
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
