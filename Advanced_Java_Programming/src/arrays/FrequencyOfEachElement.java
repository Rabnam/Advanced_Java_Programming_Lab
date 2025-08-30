package arrays;
import java.util.*;

public class FrequencyOfEachElement {
    public FrequencyOfEachElement() {
    }

    public static void main(String[] var0) {
        int[] var1 = new int[]{1, 2, 3, 5, 4, 7, 5, 4, 6, 2, 1, 4, 5, 5, 6, 7, 5, 3};
        boolean[] var2 = new boolean[var1.length];
        System.out.println("Frequency of each Unique element: ");

        for(int var3 = 0; var3 < var1.length; ++var3) {
            if (!var2[var3]) {
                int var4 = 1;

                for(int var5 = var3 + 1; var5 < var1.length; ++var5) {
                    if (var1[var3] == var1[var5]) {
                        ++var4;
                        var2[var5] = true;
                    }
                }

                System.out.println("Frequency of " + var1[var3] + " : " + var4);
            }
        }

    }
}
