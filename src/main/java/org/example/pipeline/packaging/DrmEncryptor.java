package org.example.pipeline.packaging;

import org.example.models.VideoFile;

public class DrmEncryptor {
    public void encrypt(VideoFile videoFile) {
        System.out.println("Encrypting video chunks for Widevine, FairPlay, and PlayReady on " + videoFile.getFilename());
    }
}
