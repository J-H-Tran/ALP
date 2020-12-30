package academy.learnprogramming;

import javax.management.MBeanAttributeInfo;

public class MegaByteConverter {

    public static void printMegaBytesAndKiloBytes(int kilioBytes) {

        int mB = Math.round(kilioBytes / 1024);
        int rKB = kilioBytes % 1024;

        if(kilioBytes < 0) {
            System.out.println("Invalid Value");
        } else {

            System.out.println(kilioBytes + " KB = " + mB + " MB and " + rKB + " KB");
        }
    }
}
