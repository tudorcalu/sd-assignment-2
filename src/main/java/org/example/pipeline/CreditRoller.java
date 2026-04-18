package org.example.pipeline;

import org.example.models.VideoFile;

public class CreditRoller {
    public void detectCredits(VideoFile videoFile) {
        System.out.println("Analyzing " + videoFile.getFilename() + " to detect 'Watch Next' credit trigger");
    }
}
