package com.example.Thawaq.Controller;

import com.example.Thawaq.Api.ApiResponse;
import com.example.Thawaq.Model.Rating;
import com.example.Thawaq.Service.RatingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    @GetMapping("/get-all")
    public ResponseEntity getAllRatings() {
        return ResponseEntity.status(200).body(ratingService.getAllRatings());
    }

    //User add rating (Jana) //v2
    @PostMapping("/user/add-rating/{userId}/{storeId}")
    public ResponseEntity addRatingToStore(@Valid @RequestBody Rating rating,@PathVariable Integer userId,@PathVariable Integer storeId) {
        ratingService.addRatingFromUserToStore(rating,userId,storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added User rating to store!"));
    }

    //expert add rating (Jana) V3
    @PostMapping("/expert/add-rating/{expertId}/{storeId}")
    public ResponseEntity addRatingFromExpertToStore(@Valid @RequestBody Rating rating,@PathVariable Integer expertId,@PathVariable Integer storeId) {
        ratingService.addRatingFromExpertToStore(rating,expertId,storeId);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully added Expert rating to store!"));
    }

    @PutMapping("/update-rating/{id}")
    public ResponseEntity updateRatingToStore(@PathVariable Integer id,@Valid @RequestBody Rating rating) {
        ratingService.updateRating(rating,id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated rating!"));}


    @DeleteMapping("/delete-rating/{id}")
    public ResponseEntity deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully deleted rating!"));
    }

    ///v2
    @GetMapping("/average-rating-expert/{expertId}")
    public ResponseEntity getAverageRatingExpert(@PathVariable Integer expertId) {
        ratingService.CalculateAverageRatingExpert(expertId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully average rating!"));
    }
    ///v2
    @GetMapping("/average-rating-store/{storeId}")
    public ResponseEntity getAverageRatingStore(@PathVariable Integer storeId) {
        ratingService.CalculateAverageStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully average rating!"));
    }

    //V3 Jana
    @GetMapping("/average-rating-store-service/{storeId}")
    public ResponseEntity CalculateAverageServiceStore(@PathVariable Integer storeId) {
        ratingService.CalculateAverageServiceStore(storeId);
        return ResponseEntity.status(200).body(new ApiResponse("successfully Calculate average service!"));
    }
    //V3
    @GetMapping("/top-4-cafes")
    public ResponseEntity getTop4CafesByAverageRating(){
        return ResponseEntity.status(200).body(ratingService.getTop4CafesByAverageRating());}
    //V3
    @GetMapping("/top-4-restaurant")
    public ResponseEntity getTop4RestaurantByAverageRating(){
        return ResponseEntity.status(200).body(ratingService.getTop4RestaurantByAverageRating());}




}