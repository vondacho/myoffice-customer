package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is a transactional implementation of the {@link CustomerDataService} domain class
 * Transactional aspect provided by Spring
 * Proxy pattern applied
 */
@RequiredArgsConstructor
public class TransactionalCustomerDataService implements CustomerDataService {

    @NonNull
    CustomerDataService customerDataService;

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findCustomersByFolder(FolderId folderId) {
        return findCustomersByFolder(folderId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Folder> findFoldersByCustomer(CustomerId customerId) {
        return findFoldersByCustomer(customerId);
    }
}
