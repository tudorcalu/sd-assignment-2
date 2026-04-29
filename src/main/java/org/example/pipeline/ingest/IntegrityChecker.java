package org.example.pipeline.ingest;

import org.example.models.VideoFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IntegrityChecker {

    private static final String HASH_ALGORITHM  = "SHA-256";
    private static final int    READ_BUFFER_SIZE = 8192;
    private static final String HEX_FORMAT       = "%02x";

    public boolean validateChecksum(VideoFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        try (FileInputStream fis = new FileInputStream(file.getFilename())) {
            byte[] buffer = new byte[READ_BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : hashBytes) {
            sb.append(String.format(HEX_FORMAT, b));
        }
        String calculated = sb.toString();

        if (file.getExpectedChecksum() != null && !calculated.equals(file.getExpectedChecksum())) {
            throw new SecurityException(
                "Checksum mismatch! Expected: " + file.getExpectedChecksum() + ", got: " + calculated
            );
        }

        System.out.println("  OK checksum: " + calculated);
        return true;
    }
}
