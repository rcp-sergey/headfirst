import java.util.ArrayList;

public class Cards {
    public static void main(String[] args) {
        int[] arr = new int[1000000];
        int[] cards = {-1, 0, 1};


        int length = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = cards[(int)Math.round(Math.random()*2)];
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                length = 0;
                while (i < arr.length && arr[i] == 1) {
                    i++;
                    length++;
                }

                if (length > maxLength) {
                maxLength = length;
                }
            }
            }

        System.out.println("Максимальная последовательность красных " + maxLength);
        //System.out.println(arrayList.size());


    }

}
