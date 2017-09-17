package edu.noia.myoffice.customer.domain.util.test;

import edu.noia.myoffice.customer.data.adapter.FolderStateFactoryAdapter;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FolderTestObjectFactory {

    private static final String DEFAULT_NAME = "John Doe";
    private static final String DEFAULT_NOTES = "He is very dangerous.";

    public static Folder createDefaultFolder() {
        FolderState state = new FolderStateFactoryAdapter().of(DEFAULT_NAME);
        state.setId(UUID.randomUUID());
        state.setNotes(DEFAULT_NOTES);
        return Folder.of(state);
    }

}

