package com.plachno.convert;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

/**
 * Hello world!
 */
public class ConvertApp {
    public static void main(String[] args) throws Exception {
        // z biezzcego katalogu pobieram pliki i klasyfikuje je wg ich domyślnych nazw z Connecta
        List<EmpaticaFile> empaticaFiles = FilesObtainer.obtain(args);

        //validate arguments - czyli, że w odpowiednim pliku mam odpowiednie dane
        FilesValidator.validate(empaticaFiles);

        //rozpoczynam pętlę po odczytanych plikach
        for (EmpaticaFile empaticaFile : empaticaFiles) {
            if (empaticaFile.getFileType().equals(TypesOfFiles.IBI)) {
                LineIterator lineIterator = FileUtils.lineIterator(empaticaFile.getFile());
                double startTime = Double.parseDouble(lineIterator.nextLine().split(", ")[0]);

                File output = new File(empaticaFile.getFileType().getFilename() + "_TS.csv");
                PrintWriter pw = new PrintWriter(new FileWriter(output));
                for (int i = 0; lineIterator.hasNext(); i++) {
                    //wczytaj linie
                    //splituj po przecinku
                    String [] lineElem = lineIterator.nextLine().split(",");
                    //pierwszy arg to masz delte
                    double delta = Double.parseDouble(lineElem[0].trim());
                    //drugi to masz wartość
                    double value = Double.parseDouble(lineElem[1].trim());
                    //zapisujesz: t0+delta, wartość
                    double time = startTime + delta;
                    pw.printf(Locale.US, "%f;%f%n", time, value);
                }
                pw.close();
            } else if(empaticaFile.getFileType().equals(TypesOfFiles.ACC)) {
                LineIterator lineIterator = FileUtils.lineIterator(empaticaFile.getFile());
                double startTime = Double.parseDouble(lineIterator.nextLine().split(", ")[0]);

                double delta = 1. / Double.parseDouble(lineIterator.nextLine().split(",")[0]);
                File [] output = new File[3];
                output[0] = new File(empaticaFile.getFileType().getFilename() + "_X_TS.csv");
                output[1] = new File(empaticaFile.getFileType().getFilename() + "_Y_TS.csv");
                output[2] = new File(empaticaFile.getFileType().getFilename() + "_Z_TS.csv");
                PrintWriter [] pw = new PrintWriter[3];
                for (int i = 0; i < 3; i++) {
                    pw[i] = new PrintWriter(new FileWriter(output[i]));
                }
                for (int i = 0; lineIterator.hasNext(); i++) {
                    double time = startTime + i * delta;
                    //pobieram linię
                    //splituje ją na wartości
                    String[] acc_vals = lineIterator.nextLine().split(",");
                    //do każego pliku z pw wpisuje time, alalogiczna wartosc
                    for (int j = 0; j < 3; j++) {
                        pw[j].printf(Locale.US, "%f;", time);
                        pw[j].println(acc_vals[j]);
                    }
//                    pw.printf(Locale.US, "%f,", time);
//                    pw.println(lineIterator.nextLine());
                }
                for (PrintWriter printWriter : pw) {
                    printWriter.close();
                }
            } else if(empaticaFile.getFileType().equals(TypesOfFiles.TAGS)) {
                LineIterator lineIterator = FileUtils.lineIterator(empaticaFile.getFile());
                File output = new File("TAGS_TS.csv");
                PrintWriter pw = new PrintWriter(new FileWriter(output));
                while(lineIterator.hasNext()){
                    pw.println(lineIterator.next() + "," + "DEF_TAG");
                }
                pw.close();
            } else {

                LineIterator lineIterator = FileUtils.lineIterator(empaticaFile.getFile());
                double startTime = Double.parseDouble(lineIterator.nextLine().split(", ")[0]);

                double delta = 1. / Double.parseDouble(lineIterator.nextLine().split(",")[0]);
                File output = new File(empaticaFile.getFileType().getFilename() + "_TS.csv");
                PrintWriter pw = new PrintWriter(new FileWriter(output));
                for (int i = 0; lineIterator.hasNext(); i++) {
                    double time = startTime + i * delta;
                    pw.printf(Locale.US, "%f;", time);
                    pw.println(lineIterator.nextLine());
                }
                pw.close();

            }
        }
    }
}
