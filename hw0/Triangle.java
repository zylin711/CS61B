public class Triangle {
    public static void drawTriangle(int N){
        int col = 0;
        while (col < N){
            col += 1;
            int row = 0;
            while (row < col){
                System.out.print('*');
                row += 1;
            }
            System.out.println();
        }
    }

    public static void main (String[] argu){
        drawTriangle(10);
    }
}

