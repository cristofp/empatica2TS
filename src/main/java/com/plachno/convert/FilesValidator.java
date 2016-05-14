package com.plachno.convert;

import java.util.List;

/**
 * Created by Krzysztof Płachno on 2016-05-01.
 */
public class FilesValidator {
    public static void validate(List<EmpaticaFile> empaticaFiles) throws Exception {
        //// TODO: 2016-05-01  napis tutaj tą walidację np. po liczbie kolumn
        if(false){
            throw new Exception("Wrong file format of ....");
        }
        if(empaticaFiles.isEmpty()){
            System.out.println("No empatica files found");
        }else{
            System.out.println("Found following files: ");
            for (EmpaticaFile empaticaFile : empaticaFiles) {
                System.out.println(empaticaFile.getFileType().name());
            }

        }
    }
}
