package edu.wpi.teamname.entity;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

public class Node {

  @Setter @Getter private String id, floor, building, nodeType, longName, shortName;
  @Setter @Getter private int xCoord, yCoord;
  @Getter ArrayList<Node> adjacentNodes;

  public Node(
      String id,
      String floor,
      String building,
      String nodeType,
      String longName,
      String shortName,
      int xCoord,
      int yCoord) {
    this.id = id;
    this.floor = floor;
    this.building = building;
    this.nodeType = nodeType;
    this.longName = longName;
    this.shortName = shortName;
    this.xCoord = xCoord;
    this.yCoord = yCoord;

    adjacentNodes = new ArrayList<>();
  }

  public void addAdjacent(Node n) {
    adjacentNodes.add(n);
  }

  @Override
  public String toString() {
    return "Node{"
        + "id='"
        + id
        + '\''
        + ", floor='"
        + floor
        + '\''
        + ", building='"
        + building
        + '\''
        + ", nodeType='"
        + nodeType
        + '\''
        + ", longName='"
        + longName
        + '\''
        + ", shortName='"
        + shortName
        + '\''
        + ", xCoord="
        + xCoord
        + ", yCoord="
        + yCoord
        + ", adjacentNodes="
        + adjacentNodes.size()
        + '}';
  }
}
