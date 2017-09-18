package edu.noia.myoffice.customer.domain.util.test;

import edu.noia.myoffice.customer.data.adapter.FolderStateFactoryAdapter;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FolderTestObjectFactory {

    private static final String DEFAULT_NAME = "John Doe";
    private static final String DEFAULT_NOTES = "He is very dangerous.";

    public static Folder createDefaultFolder() {
        return Folder.of(new FolderStateFactoryAdapter().of(FolderVO.builder()
            .name(DEFAULT_NAME)
            .notes(DEFAULT_NOTES)
            .build()));
    }

}

