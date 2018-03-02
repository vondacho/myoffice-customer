package edu.noia.myoffice.customer.data.test.util;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestFolder {

    private static RandomString randomString8 = new RandomString(10, ThreadLocalRandom.current());

    public static FolderState randomValid(Affiliate ...affiliates) {
        return FolderSample.of(randomString8.nextString())
                .setNotes(randomString8.nextString())
                .setAffiliates(new HashSet<>(Arrays.asList(affiliates)));
    }

    public static Folder random(Affiliate ...affiliates) {
        return Folder.ofValid(FolderId.random(), randomValid(affiliates));
    }
}
