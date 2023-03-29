package edu.wpi.teamname.controllers;

import edu.wpi.teamname.entity.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.apache.commons.collections4.map.HashedMap;

public class AlgorithmController {

  private ArrayList<Node> nodes;

  public AlgorithmController() {

    // initialize nodes
    nodes = new ArrayList<>();

    // Add all information from CSV files into the nodes ArrayList, including all edge relationships
    String line = "";
    String splitBy = ",";
    try {
      BufferedReader br =
          new BufferedReader(new FileReader("src/main/java/edu/wpi/teamname/files/L1Nodes.csv"));

      // skip header
      br.readLine();

      while ((line = br.readLine()) != null) {
        String[] nodeInfo = line.split(splitBy);
        int xcoord = Integer.parseInt(nodeInfo[1]);
        int ycoord = Integer.parseInt(nodeInfo[2]);

        Node thisNode =
            new Node(
                nodeInfo[0],
                nodeInfo[3],
                nodeInfo[4],
                nodeInfo[5],
                nodeInfo[6],
                nodeInfo[7],
                xcoord,
                ycoord);

        nodes.add(thisNode);
      }
    } catch (IOException e) {
      System.out.println("problem when adding nodes");
    }

    try {
      BufferedReader br =
          new BufferedReader(new FileReader("src/main/java/edu/wpi/teamname/files/L1Edges.csv"));

      // skip header
      br.readLine();
      while ((line = br.readLine()) != null) {
        String[] edgeInfo = line.split(splitBy);
        String ID1 = edgeInfo[1];
        String ID2 = edgeInfo[2];
        Node node1 = null, node2 = null;
        for (Node n : nodes) {
          if (n.getId().equals(ID1)) {
            node1 = n;
          }
          if (n.getId().equals(ID2)) {
            node2 = n;
          }
        }

        if (node1 == null || node2 == null) {
          System.out.println(
              "Edge " + edgeInfo[0] + " was not added, one of the nodes was not found.");
          continue;
        }

        node1.addAdjacent(node2);
        node2.addAdjacent(node1);
      }
    } catch (IOException e) {
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

    // queue is nodes currently on the stack to be visited, visited is nodes that have been explored
    // already
    ArrayList<Node> queue = new ArrayList<>();
    ArrayList<Node> visited = new ArrayList<>();

    // paths stores the paths to each node on the queue, each path maps exactly to a node in the
    // queue
    ArrayList<ArrayList<Node>> queuePaths = new ArrayList<>();

    // set current node to node with passed in start longName
    for (Node n : nodes) {
      if (n.getLongName().equals(startNodeLongName)) {
        queue.add(n);
        visited.add(n);

        ArrayList<Node> tempPath = new ArrayList<>();
        tempPath.add(n);
        queuePaths.add(tempPath);
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
        } else if (!visited.contains(n)) {

          // add the current node to both visited and queue arrayLists
          visited.add(n);
          queue.add(n);

          // update the current path with the current node, add it to the paths arrayList
          ArrayList<Node> tempList = new ArrayList<>(currPath);
          tempList.add(n);
          queuePaths.add(tempList);
        }
      }
    }

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

  /**
   * Calculates the euclidian distance between two nodes
   *
   * @param node1 first node
   * @param node2 second node
   * @return eucDis the euclidian distance
   */
  public double euclidianDistance(Node node1, Node node2) {
    int x1 = node1.getXCoord();
    int y1 = node1.getYCoord();
    int x2 = node2.getXCoord();
    int y2 = node2.getYCoord();

    double eucDis = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    return (eucDis);
  }

  /**
   * Finds the unvisited node with the lowest f-score
   *
   * @param unvisited array of unvisited nodes
   * @param fScores a hashmap of all nodes and their corresponding f-scores
   * @return minKey the node with the lowest f-score that has not been visited
   */
  public Node getMinimum(ArrayList<Node> unvisited, Map<Node, Double> fScores) {
    Double minValue = Double.MAX_VALUE;
    Double curValue = Double.MAX_VALUE;
    Node minKey = null;
    for (Node n : unvisited) {
      curValue = fScores.get(n);
      if (curValue < minValue) {
        minValue = curValue;
        minKey = n;
      }
    }
    return (minKey);
  }

  /**
   * Takes in a starting node and ending node uses a* search to find the shortest path between them.
   * The cost to come is the distance from the current node to the next node. The heuristic is the
   * euclidian distance from the start node to the next node.
   *
   * @param startNodeLongName node to start the search
   * @param endNodeLongName node to search for
   * @return shortest path from the start to the end node
   */
  public ArrayList<Node> aStarSearch(String startNodeLongName, String endNodeLongName) {
    ArrayList<Node> visited = new ArrayList<>();
    ArrayList<Node> unvisited = new ArrayList<>();

    Map<Node, Double> gScores = new HashedMap<>(); // node object and corresponding g-score
    Map<Node, Double> fScores = new HashedMap<>(); // node object and corresponding f-score
    Map<Node, Node> parentNodes =
        new HashedMap<>(); // node object and corresponding parent (previous node)

    Node startNode = null;
    Node targetNode = null;
    for (Node n : nodes) {
      unvisited.add(n);
      gScores.put(n, Double.MAX_VALUE);
      fScores.put(n, Double.MAX_VALUE);

      if (n.getLongName().equals(startNodeLongName)) {
        startNode = n;
      }
      if (n.getLongName().equals(endNodeLongName)) {
        targetNode = n;
      }
    }

    gScores.put(startNode, 0.0);
    fScores.put(startNode, 0.0);

    boolean finished = false;
    while (!finished) {
      if (unvisited.size() == 0) {
        finished = true;
      } else {
        Node curNode = getMinimum(unvisited, fScores);

        if (curNode.equals(targetNode)) {
          visited.add(curNode);
          finished = true;
        } else {
          ArrayList<Node> adjNodes = curNode.getAdjacentNodes();
          for (Node n : adjNodes) {
            if (!visited.contains(n)) {
              double newG = gScores.get(curNode) + euclidianDistance(curNode, n);
              if (newG < gScores.get(n)) {
                gScores.put(n, newG);
                fScores.put(n, (newG + euclidianDistance(startNode, n)));
                parentNodes.put(n, curNode);
              }
            }
          }
          visited.add(curNode);
          unvisited.remove(curNode);
        }
      }
    }

    ArrayList<Node> path = new ArrayList<>();
    path.add(targetNode);
    Node findingPathNode = targetNode;
    Node parentNode = null;

    while (!parentNodes.get(findingPathNode).equals(startNode)) {
      parentNode = parentNodes.get(findingPathNode);
      path.add(parentNode);
      findingPathNode = parentNode;
    }

    path.add(startNode);
    Collections.reverse(path);
    return (path);
  }
}
