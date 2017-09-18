package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.FolderVO;

import java.util.UUID;

public interface FolderState {

    UUID getId();

    FolderState setData(FolderVO data);

    FolderVO getData();
}
