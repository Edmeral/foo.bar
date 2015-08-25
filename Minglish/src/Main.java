import java.util.Arrays;
import java.util.Hashtable;

public class Main {

  static Hashtable<String, Boolean> visited =  new Hashtable<String, Boolean>();
  static int counter;
  static String[] alphabet;

  public static void dfs(Graph g, String vertex) {
    if (visited.get(vertex) == null || !visited.get(vertex)) {
      visited.put(vertex, Boolean.TRUE);
      for (String neighbor: g.getNeighbors(vertex))
        dfs(g, neighbor);
    }

    // We keep a counter to make the topological sort, the counter starts at n - 1 (n being the number of vertices
    // in the graph) thus the vertex whose dfs finishes first will be put at the end of the alphabet array
    alphabet[counter] = vertex;
    counter--;
  }

  public static String[] topSort(Graph g) {
    counter = g.size() - 1;
    alphabet = new String[counter + 1];
    for (String vertex: g.getVertices()) {
      if (visited.get(vertex) == null || !visited.get(vertex))
        dfs(g, vertex);
    }
    return alphabet;
  }

  public static String[] compare2Words(String word1, String word2) {
    for (int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
      if (word1.charAt(i) != word2.charAt(i))
        return new String[] { word1.substring(i, i + 1), word2.substring(i, i + 1) };
    }
    return null;
  }

  public static void main(String[] args) {
    String[] words = {"z", "yx", "yz"};

    // Creating the graph
    Graph alphabetGraph = new Graph();
    for (int i = 0; i < words.length - 1; i++) {
      String[] res = compare2Words(words[i], words[i + 1]);
      if (res != null)
        alphabetGraph.connect(res[0], res[1]);
    }

    alphabet = topSort(alphabetGraph);

    System.out.println(Arrays.toString(alphabet));
    // ==> [x, z, y]
  }
}
