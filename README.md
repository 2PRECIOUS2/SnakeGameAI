# SnakeGameAI
This repository contains a Snake AI game I developed as part of my Computer Science 2: Analysis of Algorithms class. The goal of this project was to implement an intelligent algorithm to control the snake and have it find the optimal path to collect apples while avoiding collisions.

In this repository, you will find the implementation of the Breadth-First Search (BFS) algorithm, which I used to navigate the snake effectively. BFS ensures that the snake explores all possible paths level by level, guaranteeing the shortest path to the apple.

Explanation of the Algorithm
Breadth-First Search (BFS)
In summary, Breadth-First Search (BFS) is a graph traversal algorithm that explores nodes in a level-wise manner. It starts at the root node (in this case, the snake's current position) and explores all its neighboring nodes before moving on to the neighbors of those nodes.

Here’s how BFS works in my SnakeAI implementation:

Initialization:
The algorithm begins with a queue. The starting position of the snake (the head) is enqueued, and it is marked as visited.

Traversal:
At each step, the algorithm dequeues the front node (current position of the snake). It then checks all possible moves (up, down, left, right). For each valid move:

If the move leads to the apple, the algorithm stops, and the path to the apple is reconstructed.
If the move is valid and hasn't been visited yet, it is added to the queue and marked as visited.
Pathfinding:
BFS guarantees the shortest path to the apple since it explores all paths level by level. The algorithm ensures that the first time it reaches the apple, the path is the shortest possible.

Edge Cases:
If no path to the apple exists (e.g., the snake is surrounded by its own body), BFS terminates, and the game handles this gracefully.
BFS avoids collisions by ensuring that invalid moves (like moving into the snake's body or walls) are ignored during traversal.

Why BFS?
I chose BFS because of its ability to find the shortest path in an unweighted grid, which is essential for maximizing the snake’s efficiency. Although BFS can be computationally expensive for larger grids or denser snake bodies, it performs well for the typical SnakeAI game scenario.

Summary:
This project helped me understand the practical applications of algorithms in real-time problem-solving. Implementing BFS allowed me to combine theoretical concepts with hands-on coding experience. 
