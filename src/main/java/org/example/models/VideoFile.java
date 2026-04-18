package org.example.models;

public class VideoFile {
    private String filename;
    private long sizeInBytes;

    public VideoFile(String filename, long sizeInBytes) {
        this.filename = filename;
        this.sizeInBytes = sizeInBytes;
    }

    public String getFilename() { return filename; }
    public long getSizeInBytes() { return sizeInBytes; }

    @Override
    public String toString() {
        return "VideoFile{" +
                "filename='" + filename + '\'' +
                ", sizeInBytes=" + sizeInBytes +
                '}';
    }
}
