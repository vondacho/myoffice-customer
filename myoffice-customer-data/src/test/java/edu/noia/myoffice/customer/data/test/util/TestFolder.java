package edu.noia.myoffice.customer.data.test.util;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestFolder {

    private static RandomString randomString8 = new RandomString(10, ThreadLocalRandom.current());

    public static FolderState randomValid(Affiliate ...affiliates) {
        return FolderSample.builder(randomString8.nextString())
                .notes(randomString8.nextString())
                .affiliates(Arrays.asList(affiliates))
                .build();
    }

    public static Folder random(Affiliate ...affiliates) {
        return Folder.ofValid(UUID.randomUUID(), randomValid(affiliates));
    }
}
