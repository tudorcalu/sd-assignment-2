package org.example.pipeline.analysis;

import org.example.models.VideoFile;

public class SceneIndexer {
    public void indexScenes(VideoFile videoFile) {
        System.out.println("Categorizing segments for " + videoFile.getFilename());
    }
}
