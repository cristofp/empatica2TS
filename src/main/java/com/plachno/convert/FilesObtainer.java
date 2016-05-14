package com.plachno.convert;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysztof Płachno on 2016-05-01.
 */
public class FilesObtainer {
    public static List<EmpaticaFile> obtain(String [] args){
        List<EmpaticaFile> empaticaFiles = new ArrayList<EmpaticaFile>();
        if( args.length == 0){
            for (File file : new File(".").listFiles()) {
                if(file.isFile()){
                    TypesOfFiles fileType = TypesOfFiles.obtainType(FilenameUtils.removeExtension(file.getName()));
                    if(fileType != null){
                        empaticaFiles.add(new EmpaticaFile(fileType, file));
                    }
                }
            }

        }else{
            //todo tutaj trzeba dorobić obsługę parametrów lini komend
        }
        return empaticaFiles;
    }
}
