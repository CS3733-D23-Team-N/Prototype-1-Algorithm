package edu.wpi.teamname.controllers;

import edu.wpi.teamname.entity.Node;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AlgorithmController {

    private ArrayList<Node> nodes;

    public AlgorithmController(String nodeFile) {
        // Add all information from CSV files into the nodes ArrayList, including all edge relationships
        //TODO: parse CSV and stuff
        String line = "";
        String splitBy = ",";
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(nodeFile));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] nodeInfo = line.split(splitBy);
                int xcoord = Integer.parseInt(nodeInfo[1]);
                int ycoord = Integer.parseInt(nodeInfo[2]);

                Node thisNode = new Node(nodeInfo[0], nodeInfo[3], nodeInfo[4], nodeInfo[5], nodeInfo[6], nodeInfo[7], xcoord, ycoord);

            }
        }
        catch (IOException e){

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
