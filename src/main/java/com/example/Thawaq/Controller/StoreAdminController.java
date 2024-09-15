package com.example.Thawaq.Controller;

import com.example.Thawaq.Api.ApiResponse;
import com.example.Thawaq.Model.StoreAdmin;
import com.example.Thawaq.Service.StoreAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store-admin")
public class StoreAdminController {
    private final StoreAdminService storeAdminService;

    @GetMapping("/get-all")
    public ResponseEntity getAll() {
        return ResponseEntity.status(200).body(storeAdminService.getAllStoreAdmins());
    }
    @GetMapping("/get-id/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(storeAdminService.getStoreAdminById(id));
    }

//V3
    @PutMapping("/activate-storeAdmin/{storeAdminId}")
    public ResponseEntity  activateStoreAdmin(@PathVariable Integer storeAdminId) {
        storeAdminService.activateStoreAdmin(storeAdminId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully Activate Store Admin"));
    }
}