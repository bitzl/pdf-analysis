package pdfanalysis;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Iterator;

public class Main {

    public static String what() {
        return "great";
    }

    public static void main(String[] args) {
        File sourceFolder = new File("C:/texlive/2014");
        String[] extensions = { "pdf" };
        Iterator<File> iterator = FileUtils.iterateFiles(sourceFolder, extensions, true);
        int count = 0;
        while (iterator.hasNext()) {
            File file = iterator.next();
            System.out.println("--> " + file.getName());
            count++;
            if (count > 100) {
                break;
            }
        }
        System.out.println(count);
    }

}
