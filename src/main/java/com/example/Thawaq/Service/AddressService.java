package com.example.Thawaq.Service;

import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.DTO.AddressDTO;
import com.example.Thawaq.Model.Address;
import com.example.Thawaq.Model.Branch;
import com.example.Thawaq.Repository.AddressRepository;
import com.example.Thawaq.Repository.BranchRepository;
import com.example.Thawaq.Repository.StoreAdminRepository;
import com.example.Thawaq.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final BranchRepository branchRepository;
    private final StoreAdminRepository storeAdminRepository;
    private final StoreRepository storeRepository;

    public List<Address> getAddresses()
    {
        return addressRepository.findAll();
    }

    public void addAddress(Integer storeAdminID,Integer storeID,AddressDTO addressDTO) //v2
    {
        if(storeRepository.findStoreById(storeID) == null)
        {
            throw new ApiException("Store not found");
        }
        if(storeAdminRepository.findStoreAdminById(storeAdminID) == null)
        {
            throw new ApiException("Store admin not found");
        }
        if(storeAdminRepository.findStoreAdminById(storeAdminID).getStore().getId() != storeAdminRepository.findStoreAdminById(storeAdminID).getId())
        {
            throw new ApiException("Not match");
        }

        Branch b = branchRepository.findBranchById(addressDTO.getBranch_id());
        if(b == null)
        {
            throw new ApiException("Branch not found");
        }
        Address a = new Address(null,addressDTO.getCity(), addressDTO.getStreet(), b);

        addressRepository.save(a);
    }

    public void updateAddress(Integer storeAdminID,Integer storeID,AddressDTO addressDTO) //v2
    {
        if(storeRepository.findStoreById(storeID) == null)
        {
            throw new ApiException("Store not found");
        }
        if(storeAdminRepository.findStoreAdminById(storeAdminID) == null)
        {
            throw new ApiException("Store admin not found");
        }
        if(storeAdminRepository.findStoreAdminById(storeAdminID).getStore().getId() != storeAdminRepository.findStoreAdminById(storeAdminID).getId())
        {
            throw new ApiException("Not match");
        }

        Address a = addressRepository.findAddressById(addressDTO.getBranch_id());
        if(a == null)
        {
            throw new ApiException("Address not found");
        }
        a.setCity(addressDTO.getCity());
        a.setStreet(addressDTO.getStreet());
        addressRepository.save(a);
    }
    public void deleteAddress(Integer id)
    {
        if(addressRepository.findAddressById(id) == null)
        {
            //            throw new ApiException("Address not found");
        }
        addressRepository.deleteById(id);
    }
}
