package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.factory.FolderStateFactory;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FolderStateFactoryAdapter implements FolderStateFactory {
    @Override
    public FolderState of(FolderVO data) {
        return JpaFolderState.of(UUID.randomUUID(), data.getName()).setData(data);
    }
}
