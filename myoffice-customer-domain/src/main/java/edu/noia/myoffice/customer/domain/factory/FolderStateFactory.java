package edu.noia.myoffice.customer.domain.factory;

import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.NonNull;

public interface FolderStateFactory {

    FolderState of(FolderVO data);

    default FolderState of(FolderState state) {
        return state;
    }
}
