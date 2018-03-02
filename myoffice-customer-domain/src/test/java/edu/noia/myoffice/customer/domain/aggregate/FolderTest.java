package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class FolderTest {

    @Test
    public void equals_contract_should_be_implemented_correctly() {
        EqualsVerifier.forClass(FolderId.class).verify();
        EqualsVerifier.forClass(FolderSample.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .withIgnoredFields("affiliates")
                .verify();
        EqualsVerifier.forClass(BaseEntity.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .withRedefinedSubclass(Folder.class)
                .withOnlyTheseFields("id")
                .verify();
    }
}
