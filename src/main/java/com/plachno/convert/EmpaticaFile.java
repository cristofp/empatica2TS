package com.plachno.convert;

import java.io.File;

/**
 * Created by Krzysztof Płachno on 2016-05-01.
 */
public class EmpaticaFile {
    private final TypesOfFiles fileType;
    private final File file;

    public EmpaticaFile(TypesOfFiles fileType, File file) {
        this.fileType = fileType;
        this.file = file;
    }

    public TypesOfFiles getFileType() {
        return fileType;
    }

    public File getFile() {
        return file;
    }
}
