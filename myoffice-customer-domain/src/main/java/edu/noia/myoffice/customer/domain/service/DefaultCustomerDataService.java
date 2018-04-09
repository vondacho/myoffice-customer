package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.common.domain.util.EntityFinder;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class DefaultCustomerDataService implements CustomerDataService {

    @NonNull
    private CustomerRepository customerRepository;
    @NonNull
    private FolderRepository folderRepository;

    public List<Customer> findCustomersByFolder(FolderId folderId) {
        return EntityFinder.find(Folder.class, folderId, folderRepository::findOne)
                .getAffiliates()
                .stream()
                .map(affiliate -> EntityFinder.find(Customer.class, affiliate.getCustomerId(), customerRepository::findOne))
                .collect(toList());
    }

    public List<Folder> findFoldersByCustomer(CustomerId customerId) {
        return folderRepository.findAllByAffiliate(customerId);
    }
}
