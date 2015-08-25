import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Set;

public class Graph {
  private Hashtable<String, ArrayList<String>> adjList = new Hashtable<>();

  public void connect(String c1, String c2) {
    ArrayList<String> c1Neighbors = adjList.get(c1);
    ArrayList<String> c2Neighbors = adjList.get(c2);

    // If c1 and c2 don't exist, add them to the graph
    if (c1Neighbors == null)
      adjList.put(c1, new ArrayList<String>());
    if (c2Neighbors == null)
      adjList.put(c2, new ArrayList<String>());

    adjList.get(c1).add(c2);
  }

  public ArrayList<String> getNeighbors(String c) {
    return adjList.get(c);
  }

  public Set<String> getVertices() {
    return adjList.keySet();
  }

  // How many vertices?
  public int size() {
    return adjList.keySet().size();
  }
}
