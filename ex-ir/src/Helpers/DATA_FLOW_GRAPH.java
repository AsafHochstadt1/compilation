package Helpers;
import java.util.*;

import IR.*;
import TEMP.*;
import TYPES.*;
import SYMBOL_TABLE.*;
import AST.*;


public class DATA_FLOW_GRAPH {
	public DATA_FLOW_GRAPH.DATA_FLOW_NODE root;
	
	public DATA_FLOW_GRAPH(){
	
	this.root = new DATA_FLOW_GRAPH.DATA_FLOW_NODE("root");
	}
	
	
	public void connect (DATA_FLOW_GRAPH.DATA_FLOW_NODE node1, DATA_FLOW_GRAPH.DATA_FLOW_NODE node2){
	    node1.forward.add(node2);
	    node2.backward.add(node1);
	    checkNode(node2);
	}
	
	public void checkNode(DATA_FLOW_GRAPH.DATA_FLOW_NODE node){
	    if (node.label_name.equals("root")){ return;}
	    ArrayList<String> allocated_temp;
	    ArrayList<ASSIGNED_VAR> assigned_temp;
	    if (node.backward.size() != 0 && node.backward != null){
	      allocated_temp = new ArrayList<String>(node.backward.get(0).allocated);
	      assigned_temp = new ArrayList<ASSIGNED_VAR>(node.backward.get(0).assigned);
	      if (node.backward.size() > 1){
	      for (int i = 1; i < node.backward.size(); i ++){
	          allocated_temp.retainAll(node.backward.get(i).allocated);
	          assigned_temp.retainAll(node.backward.get(i).assigned);
	          }
	         } 
	      Set<String> allocated_union = new LinkedHashSet(node.allocated);
	      Set<ASSIGNED_VAR> assigned_union = new LinkedHashSet(node.assigned);
	      allocated_union.addAll(allocated_temp);
	      assigned_union.addAll(assigned_temp);
	      node.assigned = new ArrayList<ASSIGNED_VAR>(assigned_union);
	      node.allocated = new ArrayList<String>(allocated_union);
	      }
	      }
	      
	
	public DATA_FLOW_NODE find_label(String label){
	    DATA_FLOW_NODE res = null;
	    ArrayList<DATA_FLOW_NODE> root_point = FunctionHelpers.graph.root.forward; 
	    for (int i = 0; i > root_point.size(); i++){
	        if ((root_point.get(i).label_name).equalsIgnoreCase(label)){
	            res = root_point.get(i);
	            System.out.print("found label! \n");
	    }
	      }
	          return res;
	    }
  
	
	public class DATA_FLOW_NODE {
	public ArrayList<DATA_FLOW_NODE> forward;
	public ArrayList<DATA_FLOW_NODE> backward;
	public String label_name;
	public ArrayList <ASSIGNED_VAR> assigned;
	public ArrayList <String> allocated;
	
	public DATA_FLOW_NODE(String label, DATA_FLOW_NODE curr){
		this.label_name = label;
		this.forward = new ArrayList<DATA_FLOW_NODE>();
		this.backward = new ArrayList<DATA_FLOW_NODE>();
		this.assigned = new ArrayList<ASSIGNED_VAR>();
		this.allocated = new ArrayList<String>();
		if (curr.label_name.equals("root")){
		  curr.forward.add(this);}
		else{
		  this.backward.add(curr);
		  FunctionHelpers.graph.root.forward.add(this);}
		}
		
		public DATA_FLOW_NODE(String label){
		this.label_name = label;
		this.forward = new ArrayList<DATA_FLOW_NODE>();
		this.assigned = new ArrayList<ASSIGNED_VAR>();
		this.allocated = new ArrayList<String>();
		FunctionHelpers.currNode = this;
		}
		
		public void printGraph(){
	    System.out.print(this.label_name + " ");
	    for (int i = 0; i < this.forward.size(); i++){
	        System.out.print(" -> ");
	        this.forward.get(i).printGraph();
	        System.out.print("\n");
	    }
	
	}
		
	
	}
		
	}
	
	
