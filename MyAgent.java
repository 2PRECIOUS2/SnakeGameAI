import za.ac.wits.snake.DevelopmentAgent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MyAgent extends DevelopmentAgent {
    private int apx;
    private int apy;
    private int hx;
    private int hy;

    public static void main(String args[]) {
        MyAgent agent = new MyAgent();
        MyAgent.start(agent, args);
    }

    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String initString = br.readLine();
           
            String[] temp = initString.split(" ");
            int numberOfSnakes = Integer.parseInt(temp[0]);
            int numObstacles = 3;

            while (true) {
                String line = br.readLine();
                if (line.contains("Game Over")) {
                    break;
                }

                String appleLine = line;
                String[] appleCoord = appleLine.split(" ");
                int appleX = Integer.parseInt(appleCoord[0]);
                int appleY = Integer.parseInt(appleCoord[1]);
                apx = appleX;
                apy = appleY;

                int[][] updatedMatrix = new int[100][100];

                for (int j = 0; j < numObstacles; j++) {
                    MakeMatrix.updateMatrix(updatedMatrix, br.readLine(),1);
                }

                String[] parts;
                int mySnakeNum = Integer.parseInt(br.readLine());

                for (int i = 0; i < numberOfSnakes; i++) {
                    String snakeLine = br.readLine();
                    parts = snakeLine.split(" ");
                    String status = parts[0];

                    if (status.equals("alive")) {
                        if (i == mySnakeNum) {
                            hx = Integer.parseInt(parts[3].split(",")[0]);
                            hy = Integer.parseInt(parts[3].split(",")[1]);
                            String snakeCoordinates = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                            MakeMatrix.updateMatrix(updatedMatrix, snakeCoordinates, 1);

                        } else {
                            String snakeCoordinates = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));
                            MakeMatrix.updateMatrix(updatedMatrix, snakeCoordinates, 1);
                        }
                    }
                }

                //printMatrix(updatedMatrix);
                
               // Create your BFS object, assuming you have already defined the BFS class and updatedMatrix.
                BFS pathFinder = new BFS(updatedMatrix, hx, hy, apx, apy);

                // Find the shortest path.
                List<BFS.Node> path = pathFinder.findPath();

                if(path != null && !path.isEmpty())
                {

                    BFS.Node nextNode = path.get(1); // Assuming the next node is at index 1

                int nextX = nextNode.getX();
                int nextY = nextNode.getY();

                int moves;

                // Use a switch statement to determine the next move based on the position difference
                switch (nextX - hx) {
                    case 0:
                        switch (nextY - hy) {
                            case -1:
                                moves = 0; // 0: Up
                                break;
                            case 1:
                                moves = 1; // 1: Down
                                break;
                            default:
                                moves = -1; // Invalid move
                                break;
                        }
                        break;
                    case -1:
                        moves = 2; // 2: Left
                        break;
                    case 1:
                        moves = 3; // 3: Right
                        break;
                    default:
                        moves = -1; // Invalid move
                        break;
                }

                System.out.println(moves);
                }
                else
                {
                       for(int i = 0; i < 4; i++)
                       {
                    	   if(isValid(i,updatedMatrix, hx, hy))
                    	   {
                    		   System.out.println(i);
                    		   break;
                    	   }
                       }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private boolean isValid(int move, int[][] grid, int x, int y) {
        int maxX = grid.length - 1;
        int maxY = grid[0].length - 1;

        switch (move) {
            case 0: // Up
                return (y > 0 && grid[x][y - 1] == 0); // Check if moving up is within bounds and not blocked.
            case 1: // Down
                return (y < maxY && grid[x][y + 1] == 0); // Check if moving down is within bounds and not blocked.
            case 2: // Left
                return (x > 0 && grid[x - 1][y] == 0); // Check if moving left is within bounds and not blocked.
            case 3: // Right
                return (x < maxX && grid[x + 1][y] == 0); // Check if moving right is within bounds and not blocked.
            default:
                return false; // Invalid move
        }
    }

    
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.err.print(value);
            }
            System.err.println();
        }
        System.err.println(); 
    }
}
