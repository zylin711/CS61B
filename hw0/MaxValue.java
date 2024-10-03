public class MaxValue {
    public static int maxNumber(int x, int y){
        if (x > y){
            return x;
        }
        return y;
    }

    public static int max(int[] m){
        int current_max = m[0];
        for (int i = 1; i < m.length; i += 1){
            current_max = maxNumber(current_max, m[i]);
        }
        return current_max;
    }

    public static void main(String[] args){
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(max(numbers));
    }
}



