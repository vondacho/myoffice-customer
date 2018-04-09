package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;

import java.util.List;

public interface CustomerDataService {

    List<Customer> findCustomersByFolder(FolderId folderId);

    List<Folder> findFoldersByCustomer(CustomerId customerId);
}
