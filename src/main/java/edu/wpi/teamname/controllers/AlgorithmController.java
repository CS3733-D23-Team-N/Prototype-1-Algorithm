package edu.wpi.teamname.controllers;

import edu.wpi.teamname.entity.Node;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AlgorithmController {

    private ArrayList<Node> nodes;

    public AlgorithmController(String nodeFile, String edgeFile) {
        // Add all information from CSV files into the nodes ArrayList, including all edge relationships
        String line = "";
        String splitBy = ",";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(nodeFile));
            while ((line = br.readLine()) != null)
            {
                String[] nodeInfo = line.split(splitBy);
                int xcoord = Integer.parseInt(nodeInfo[1]);
                int ycoord = Integer.parseInt(nodeInfo[2]);

                Node thisNode = new Node(nodeInfo[0], nodeInfo[3], nodeInfo[4], nodeInfo[5], nodeInfo[6], nodeInfo[7], xcoord, ycoord);
                nodes.add(thisNode);
            }
        }
        catch (IOException e){
            System.out.println("problem when adding nodes");
        }

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(edgeFile));
            while ((line = br.readLine()) != null)
            {
                String[] edgeInfo = line.split(splitBy);
                String ID1 = edgeInfo[1];
                String ID2 = edgeInfo[2];
                Node node1, node2;
                for (Node n:nodes){
                    if(n.getId() == ID1){
                        node1 = n;
                    }
                    if(n.getId() == ID2){
                        node2 = n;
                    }
                }
                node1.addAdjacent(node2);
                node2.addAdjacent(node1);
            }
        }
        catch (IOException e){
            System.out.println("problem when adding edges");
        }
    }

    /**
     * Takes in a starting node and ending node uses breadth first search to find the shortest path between them.
     *
     * @param startNodeID node to start the search
     * @param endNodeID node to search for
     * @return shortest path from the start to the end node
     */
    public ArrayList<Node> breadthfirstSearch(String startNodeID, String endNodeID) {
        return null;
        //TODO: Write search algorithm
    }
}
