public class MakeMatrix {
    public static void updateMatrix(int[][] matrix, String snakeCoordinates, int snakeNumber) {
        String[] points = snakeCoordinates.split(" ");
        for (int i = 0; i < points.length - 1; i++) {
            String[] point1 = points[i].split(",");
            String[] point2 = points[i + 1].split(",");
            drawLine(matrix, point1, point2, snakeNumber);
        }
    }

    private static void drawLine(int[][] matrix, String[] point1, String[] point2, int snakeNumber) {
        int x1 = Integer.parseInt(point1[0]);
        int y1 = Integer.parseInt(point1[1]);
        int x2 = Integer.parseInt(point2[0]);
        int y2 = Integer.parseInt(point2[1]);
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2);
        int maxY = Math.max(y1, y2);
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                matrix[y][x] = snakeNumber;
            }
        }
    }
}
