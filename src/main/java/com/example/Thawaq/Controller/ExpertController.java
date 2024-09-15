package com.example.Thawaq.Controller;

import com.example.Thawaq.Api.ApiResponse;
import com.example.Thawaq.Service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/expert")
public class ExpertController {
    private final ExpertService expertService;

    @GetMapping("/get-all")
    public ResponseEntity getAllExperts() {
        return ResponseEntity.status(200).body(expertService.getAllExperts());
    }

    @GetMapping("/get-id/{expertId}")
    public ResponseEntity getExpertById(@PathVariable Integer expertId) {
        return ResponseEntity.status(200).body(expertService.getExpertById(expertId));
    }

    @PutMapping("/approve/{expertId}/{rejectId}")
    public ResponseEntity approve (@PathVariable Integer expertId, @PathVariable Integer rejectId) {
        expertService.approveRequest(expertId, rejectId);
        return ResponseEntity.status(200).body(new ApiResponse("Request approved"));
    }

    @PutMapping("/reject/{expertId}/{rejectId}")
    public ResponseEntity reject (@PathVariable Integer expertId, @PathVariable Integer rejectId) {
        expertService.rejectRequest(expertId, rejectId);
        return ResponseEntity.status(200).body(new ApiResponse("Request rejected"));
    }

    //V3
    @PutMapping("/activate-expert/{expertId}")
    public ResponseEntity activateExpert(@PathVariable Integer expertId) {
        expertService.activateExpert(expertId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully Activate Expert"));
    }

}