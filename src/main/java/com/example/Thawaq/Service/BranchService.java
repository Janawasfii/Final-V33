package com.example.Thawaq.Service;

import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.Model.*;
import com.example.Thawaq.Repository.BranchRepository;
import com.example.Thawaq.Repository.MenuRepository;
import com.example.Thawaq.Repository.StoreAdminRepository;
import com.example.Thawaq.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final StoreAdminRepository storeAdminRepository;
    private final MenuRepository menuRepository;

    public List<Branch> getBranches()
    {
        return branchRepository.findAll();
    }

    public void addBranch(Integer storeAdminID,Integer storeID, Branch branch) //v2
    {
        Store s = storeRepository.findStoreById(storeID);
        StoreAdmin sa = storeAdminRepository.findStoreAdminById(storeAdminID);
        if(s == null)
        {
            throw new ApiException("Store not found");
        }
        if(sa == null)
        {
            throw new ApiException("Store admin not found");
        }
        if(!sa.getStore().getId().equals(sa.getId()))
        {
            throw new ApiException("Not match");
        }
        branch.setStore(s);
        branchRepository.save(branch);
    }
    public void updateBranch(Integer storeAdminID,Integer storeID,Integer branchID,Branch branch) //v2
    {
        Branch b = branchRepository.findBranchById(branchID);
        if(b == null)
        {
            throw new ApiException("Branch not found");
        }
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
            throw new ApiException("Not match between store admin and store");
        }
        if(branchRepository.findBranchById(branchID).getStore().getId() != branchRepository.findBranchById(branchID).getId())
        {
            throw new ApiException("Not match between branch and store");
        }
        b.setName(branch.getName());
        b.setOpen(branch.isOpen());
        branchRepository.save(b);
    }
    public void deleteBranch(Integer bID)
    {
        if(branchRepository.findBranchById(bID) == null)
        {
            //            throw new ApiException("Branch not found");
        }
        branchRepository.deleteById(bID);
    }

    public void openBranch(Integer storeAdminId , Integer branchID){
        StoreAdmin s = storeAdminRepository.findStoreAdminById(storeAdminId);
        if (s == null){
            throw new ApiException("Store not found");
        }
        Branch b = branchRepository.findBranchById(branchID);
        if (b == null){
            throw new ApiException("Branch not found");
        }
        if (!b.getStore().equals(s.getStore())){
            throw new ApiException("Store not match");
        }
        b.setOpen(true);
        branchRepository.save(b);
    }
    public void closeBranch(Integer storeAdminId , Integer branchID){
        StoreAdmin s = storeAdminRepository.findStoreAdminById(storeAdminId);
        if (s == null){
            throw new ApiException("Store not found");
        }
        Branch b = branchRepository.findBranchById(branchID);
        if (b == null){
            throw new ApiException("Branch not found");
        }
        if (!b.getStore().equals(s.getStore())){
            throw new ApiException("Store not match");
        }
        b.setOpen(false);
        branchRepository.save(b);
    }

}