/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TaKuma
 */

class vertexNode {
    String data;
    ArrayList<edgeNode> next;
    
    public vertexNode(String s) {
        data = s;
        next = new ArrayList<edgeNode>();
    }
}

class edgeNode{
    String data;
    
    public edgeNode(String s){
        data = s;
    }
}

public class Graph {
    ArrayList<vertexNode> vertices = new ArrayList<vertexNode>(); //creates an adjacency list
    Map<String, Integer> hashTable = new HashMap<String, Integer>();
    String transactions;
    //MaintenanceGUI gui;
    
    public Graph(){
        
    }
    
    public void setTransactions(String s){
        transactions = s;
    }
    public void addvertex(String v){
        if (hashTable.containsKey(v)){
        }
        else { 
           vertices.add(new vertexNode(v));
           hashTable.put(v, 1);
        }
    }
    
    public int addEdge(String v1, String v2){
        if(v1.equals("") || v2.equals("")){
            return -1;
        }
        else
            for(vertexNode vertex:vertices){
                if (vertex.data.equals(v1))
                    vertex.next.add(new edgeNode(v2));
            }
        return 0;
    }
    
    public String printVertices(String t1, String t2){
        String save = "";
        int count = 0;
        for (vertexNode vertex : vertices) {
            String v = vertex.data;
            if(v.equals(t1) == false && v.equals(t2) == false){
                //System.out.print(v + " ");
                save = save + v + " ";
                count++;
            }
        }
        save = save + "   - No. of Modules:" + count + " -";;
        System.out.println();
        return save;
    }
    

    public ArrayList<ArrayList<String>> allpath(String s, String t){
        ArrayList<String> outputList = new ArrayList<>();
        ArrayList<ArrayList<String>> savedLists = new ArrayList<ArrayList<String>>() ;
        allPaths(s,t,outputList, savedLists);
        return savedLists;
    }
    
    public void allPaths(String s, String t, ArrayList<String> outputList, ArrayList<ArrayList<String>> savedLists ){
        int index = findIndex(s);  
        outputList.add(s);
        if (s.equals(t)){
            /*for(String out:outputList){
                System.out.print(out + " ");
            }*/
            savedLists.add(outputList);
            System.out.println();
            return;
        }
        else {
            for(edgeNode edge: vertices.get(index).next){
                ArrayList<String> listcopy = new ArrayList<String>(outputList);
                allPaths(edge.data,t, listcopy, savedLists);
            }
        }
    }
    
    public ArrayList<String> explode(String vertex){
        Map<String, Integer> prevCall = new HashMap<String, Integer>();
        ArrayList<String> save = new ArrayList<String>();
        explode_r(vertex, 0, prevCall, save);
        return save;
    }
    
    public void explode_r(String vertex, int spaces, Map<String, Integer> prevCall, ArrayList<String> save){
        int index = findIndex(vertex);
        String temp = "";
        if (index == -1){
            System.out.println("Vertex Not in Graph");
        }
        for(int i = 0; i < spaces; i++){
           // System.out.print(" ");
            temp = temp + " ";
        }
        if (prevCall.containsKey(vertex)){
           // System.out.print(vertices.get(index).data + "*");
            temp = temp + vertices.get(index).data + "*";
            save.add(temp);
            return;
        }
        else{
           // System.out.print(vertices.get(index).data);
            temp = temp + vertices.get(index).data;
            save.add(temp);
            prevCall.put(vertex, 1);      
        }
        spaces++;
        //System.out.println();
        for(edgeNode edge: vertices.get(index).next){
            explode_r(edge.data, spaces, prevCall, save);
        }
    }
    
    public int findIndex(String s){
        for (vertexNode v: vertices){
            if (v.data.equals(s))
                return vertices.indexOf(v);
        }
        return -1;
    }

    
}
