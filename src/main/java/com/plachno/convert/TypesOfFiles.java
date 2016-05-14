package com.plachno.convert;

/**
 * Created by Krzysztof Płachno on 2016-05-01.
 */
public enum TypesOfFiles {
    ACC("ACC", 3), BVP("BVB", 1), EDA("EDA", 1), HR("HR", 1), IBI("IBI"), TEMP("TEMP", 1), TAGS("tags");

    private String filename;
    private int dataColumns;

    TypesOfFiles(String filename) {
        this.filename = filename;
    }

    TypesOfFiles(String filename, int dataColumns) {
        this.filename = filename;
        this.dataColumns = dataColumns;
    }

    public String getFilename() {
        return filename;
    }

    public int getDataColumns() {
        return dataColumns;
    }

//    private static List<String> fileNames;
//    public static List<String> getFileNames(){
//        if(fileNames == null){
//            fileNames = new ArrayList<String>();
//            for (TypesOfFiles typeOfFile : values()) {
//                fileNames.add(typeOfFile.filename);
//            }
//        }
//        return fileNames;
//    }

    public static TypesOfFiles obtainType(String name){
        for (TypesOfFiles typeOfFile : values()) {
            if(typeOfFile.getFilename().equals(name)){
                return typeOfFile;
            }
        }
        return null;
    }
}
