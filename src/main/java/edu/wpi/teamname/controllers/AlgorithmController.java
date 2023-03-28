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
        catch (Exception e){
            System.out.println("problem when adding edges");
        }
    }

  /**
   * Takes in a starting node and ending node uses breadth first search to find the shortest path
   * between them.
   *
   * @param startNodeLongName node to start the search
   * @param endNodeLongName node to search for
   * @return shortest path from the start to the end node
   */
  public ArrayList<Node> breadthFirstSearch(String startNodeLongName, String endNodeLongName) {

    // queue is nodes currently on the stack to be visited, visited is nodes that have been explored already
    ArrayList<Node> queue = new ArrayList<>();
    ArrayList<Node> visited = new ArrayList<>();

    // paths stores the paths to each node on the queue, each path maps exactly to a node in the queue
    ArrayList<ArrayList<Node>> queuePaths = new ArrayList<>();

    // set current node to node with passed in start longName
    for (Node n : nodes) {
      if (n.getLongName().equals(startNodeLongName)) {
        queue.add(n);
        visited.add(n);

        ArrayList<Node> path = new ArrayList<>();
        path.add(n);
        queuePaths.add(path);
        break;
      }
    }

    while (queue.size() > 0) {
      Node currNode = queue.remove(0);
      ArrayList<Node> currPath = queuePaths.remove(0);
      visited.add(currNode);

      for (Node n : currNode.getAdjacentNodes()) {
        if (n.getLongName().equals(endNodeLongName)) {
          currPath.add(n);
          return currPath;
        } else if (!visited.contains(currNode)) {

          // add the current node to both visited and queue arrayLists
          visited.add(currNode);
          queue.add(currNode);

          // update the current path with the current node, add it to the paths arrayList, then remove it to be used again.
          currPath.add(n);
          queuePaths.add(currPath);
          currPath.remove(n);
        }
      }
    }

    System.out.println("Either the starting node or ending node was not in the map.");
    return null;
  }

  /**
   * Checks the nodes in this class for a node with a given longName, returning true if one exists.
   *
   * @param longName name to be checked
   * @return whether the node exists in the map or not
   */
  public boolean nodeExists(String longName) {
    for (Node n : nodes) {
      if (longName.equals(n.getLongName())) return true;
    }
    return false;
  }
}
