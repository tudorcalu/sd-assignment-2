package org.example.pipeline.packaging;

import org.example.models.VideoFile;

public class PackagingService {
    private ManifestGenerator manifestGenerator;
    private DrmEncryptor drmEncryptor;
    private BundleBuilder bundleBuilder;

    public PackagingService() {
        this.manifestGenerator = new ManifestGenerator();
        this.drmEncryptor = new DrmEncryptor();
        this.bundleBuilder = new BundleBuilder();
    }

    public void process(VideoFile videoFile) {
        System.out.println("Starting Packaging Phase");
        manifestGenerator.generateManifests(videoFile);
        drmEncryptor.encrypt(videoFile);
        bundleBuilder.buildBundle(videoFile);
        System.out.println("Packaging Phase completed successfully");
    }
}
