import java.util.*;

public class BFS {
    private final int[][] grid;
    private final int width;
    private final int height;
    private final Node startNode;
    private final Node endNode;

    public BFS(int[][] updatedMatrix, int startX, int startY, int endX, int endY) {
        this.grid = updatedMatrix;
        this.width = updatedMatrix[0].length;
        this.height = updatedMatrix.length;
        this.startNode = new Node(startX, startY);
        this.endNode = new Node(endX, endY);
    }

    public List<Node> findPath() {
        if (startNode.equals(endNode)) {
            return Collections.singletonList(startNode);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);

        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(startNode, null);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.equals(endNode)) {
                return reconstructPath(parentMap, current);
            }

            for (Node neighbor : getSafeNeighbors(current)) {
                if (!parentMap.containsKey(neighbor)) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }

        return findPathToClearing(parentMap);
    }
    
    private List<Node> findPathToClearing(Map<Node, Node> parentMap) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        // Start from the current position and explore in a BFS manner.
        queue.add(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Check if this is a "clearing." Modify this condition as per your game's rules.
            if (isClearing(current)) {
                // Reconstruct the path to this clearing and return it.
                return reconstructPath(parentMap, current);
            }

            for (Node neighbor : getSafeNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    // Update the parentMap for the neighbor.
                    parentMap.put(neighbor, current);
                }
            }
        }

        return null; // No path to a clearing found.
    }


    private boolean isClearing(Node node) {
        // Define your criteria for a "clearing" here.
        // For example, if a clearing is an area without obstacles (grid value 1), you can check for that here.
        int x = node.getX();
        int y = node.getY();
        return isValidCoordinate(x, y) && grid[y][x] == 0; // Clearing criteria: grid value is 0 (no obstacles).
    }


    private List<Node> getSafeNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        int[][] moves = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
        };

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (isValidCoordinate(newX, newY) && isSafeMove(newX, newY)) {
                neighbors.add(new Node(newX, newY));
            }
        }

        return neighbors;
    }

    private boolean isSafeMove(int x, int y) {
        return isValidCoordinate(x, y) && !collidesWithObstacleOrSnake(new Node(x, y));
    }
    
    private boolean collidesWithObstacleOrSnake(Node node) {
        int x = node.getX();
        int y = node.getY();

        if (x < 0 || x >= width || y < 0 || y >= height) {
            return true; // Check if the node is out of bounds, which is usually considered an obstacle.
        }

        double value = grid[y][x];

        if (value == 1) {
            return true; // Check for obstacles in the grid.
        }

        return false; // No obstacles found.
    }


    private List<Node> reconstructPath(Map<Node, Node> parentMap, Node endNode) {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public static class Node {
        private final int x;
        private final int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Node node = (Node) obj;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
