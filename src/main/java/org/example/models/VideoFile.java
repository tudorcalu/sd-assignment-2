package org.example.models;

public class VideoFile {
    private String filename;
    private long sizeInBytes;
    private String expectedChecksum;

    public VideoFile(String filename, long sizeInBytes, String expectedChecksum) {
        this.filename = filename;
        this.sizeInBytes = sizeInBytes;
        this.expectedChecksum = expectedChecksum;
    }

    public String getFilename() { return filename; }
    public long getSizeInBytes() { return sizeInBytes; }
    public String getExpectedChecksum() { return expectedChecksum; }

    public String getOutputDirectory() {
        return "movie_101"; 
    }

    @Override
    public String toString() {
        return "VideoFile{filename='" + filename + "'}";
    }
}
