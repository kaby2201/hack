import java.util.Random;

public class PrøveFil {

    public static void main(String[] args) {

        Random r = new Random();
        int[] distribusjon = new int[3];

        for(int i = 0; i < 100; i++) {
            int tall = r.nextInt(3);
            //System.out.println("Tallet er: " + tall);
            distribusjon[ tall ]++;
        }

        for(int i = 0; i < 3; i++) {
            System.out.println("Antall " + i + ": " + distribusjon[i]);
        }
    }
}
