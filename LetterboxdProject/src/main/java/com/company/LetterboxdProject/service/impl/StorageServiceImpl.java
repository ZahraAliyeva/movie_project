package com.company.LetterboxdProject.service.impl;

import com.company.LetterboxdProject.service.inter.StorageServiceInter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageServiceInter {

    private final ResourceLoader resourceLoader;
    private StorageOptions storageOptions;
    @Value("${firebase.storage.bucket-name}")
    private String BUCKET_NAME;

    @Value("${firebase.storage.gs}")
    private String gs;

    @PostConstruct
    private void initializeFirebase() throws Exception {
        this.storageOptions = StorageOptions.newBuilder().setCredentials(GoogleCredentials
                .fromStream(resourceLoader.getResource("classpath:firebase.json").getInputStream())).build();
    }
    @Override
    public URL uploadImage(UUID uuid, byte[] image) {
        final Storage storage = storageOptions.getService();
        final BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(BUCKET_NAME, uuid.toString())).build();
        storage.create(blobInfo, image);
        return storage.signUrl(blobInfo, 3600, TimeUnit.DAYS);
    }

    @Override
    public byte[] downloadImage(UUID uuid) throws IOException {
        final Storage storage = storageOptions.getService();
        final Blob blob = storage.get(BlobId.of(BUCKET_NAME, uuid.toString()));

        return blob.getContent();
    }

    @Override
    public void removeImage(UUID uuid) {
        final Storage storage = storageOptions.getService();
        storage.delete(BlobId.of(BUCKET_NAME, uuid.toString()));

    }

    @Override
    public void removeImages(List<UUID> uuids) {
        final Storage storage = storageOptions.getService();
        final List<BlobId> blobIds = uuids.stream().map(uuid -> BlobId.of(BUCKET_NAME, uuid.toString())).collect(Collectors.toList());
        storage.delete(blobIds);
    }
}
