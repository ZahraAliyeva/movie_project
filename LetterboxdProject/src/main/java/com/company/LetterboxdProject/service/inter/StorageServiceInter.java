package com.company.LetterboxdProject.service.inter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

public interface StorageServiceInter {

    URL uploadImage(final UUID uuid, final byte[] image);

    byte[] downloadImage(final UUID uuid) throws IOException;

    void removeImage(final UUID uuid);

    void removeImages(final List<UUID> uuids);
}
