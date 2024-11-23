package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {

    public static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static int keyboardLen = keyboard.length();

    public static void main(String[] args) {
        GuitarString[] guitarStrings = new GuitarString[keyboardLen];

        for (int i = 0; i < keyboardLen; i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIndex = keyboard.indexOf(key);

                if (keyIndex != -1) {
                    guitarStrings[keyIndex].pluck();
                }
            }

                /* compute the superposition of samples */
                double sample = 0.0;
                for (int i = 0; i < keyboardLen; i++) {
                    sample += guitarStrings[i].sample();
                }

                /* play the sample on standard audio */
                StdAudio.play(sample);

                /* advance the simulation of each guitar string by one step */
                for (int i = 0; i < keyboardLen; i++) {
                    guitarStrings[i].tic();
                }
            }
        }
}
