package com.example.Thawaq.Service;

import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.Model.Expert;
import com.example.Thawaq.Model.Request;
import com.example.Thawaq.Model.StoreAdmin;
import com.example.Thawaq.Repository.ExpertRepository;
import com.example.Thawaq.Repository.RequestRepository;
import com.example.Thawaq.Repository.StoreAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RequestService {

    private final RequestRepository requestRepository;
    private final ExpertRepository expertRepository;
    private final StoreAdminRepository storeAdminRepository;

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    //V3
    public void addRequest(Request request,Integer storeAdminId,Integer expertId) {
        StoreAdmin storeAdmin = storeAdminRepository.findStoreAdminById(storeAdminId);
        Expert e = expertRepository.findExpertById(expertId);
        if(storeAdmin == null){
            throw new ApiException("Store admin not found");}
        if(e == null){
            throw new ApiException("Expert not found");}
        if(!storeAdmin.isActive()){
            throw new ApiException("Store admin is not active");}
        if(!e.isActive()){
           throw new ApiException("Expert is not active");}
        request.setStatus(Request.Status.PENDING);
        request.setExpert(e);
        request.setStore(storeAdmin.getStore());
        e.setActive(true);
        expertRepository.save(e);
        requestRepository.save(request);}



    public void updateRequest(Request request,Integer id) {
        Request request1 = requestRepository.findRequestById(id);
        if(request1 == null) {
            throw new ApiException("Request not found");
        }
        request1.setDescription(request.getDescription());
        request1.setStatus(request.getStatus());
        request1.setPrice(request.getPrice());
        request1.setRequestDate(request.getRequestDate());
        requestRepository.save(request1);}


    public void deleteRequest(Integer id) {
        Request r = requestRepository.findRequestById(id);
        if(r == null) {
            throw new ApiException("Request not found");
        }
        requestRepository.delete(r);
    }
}