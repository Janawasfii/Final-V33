package com.example.Thawaq.Service;

import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.Model.*;
import com.example.Thawaq.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class RatingService {
    private final RatingRepository ratingRepository;
    private final ExpertRepository expertRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreAdminRepository storeAdminRepository;

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    //Add rating from the user to the store  v3
    public void addRatingFromUserToStore(Rating rating,Integer userId, Integer storeId) {
        User u = userRepository.findUserById(userId);
        Store s = storeRepository.findStoreById(storeId);
        StoreAdmin sa = storeAdminRepository.findStoreAdminById(storeId);
        if(u==null) {
            throw new ApiException("User not found");}
        if(s==null) {
            throw new ApiException("Store not found");}
        if(sa== null || !sa.isActive()) {
            throw new ApiException("Store Admin is not active");}
        rating.setStore(s);
        rating.setClient(rating.getClient());
        userRepository.save(u);
        ratingRepository.save(rating);}

    //Add rating from expert to the store  v3
    public void addRatingFromExpertToStore(Rating rating,Integer expertId, Integer storeId) {
        Expert e =expertRepository.findExpertById(expertId);
        Store s = storeRepository.findStoreById(storeId);
        StoreAdmin sa = storeAdminRepository.findStoreAdminById(storeId);
        if(e==null) {
            throw new ApiException("User not found");}
        if(s==null) {
            throw new ApiException("Store not found");}
        if(sa== null ) {
            throw new ApiException("Store Admin not found");}
        if(!e.isActive()){
            throw new ApiException("Expert is not active");}
        if(!sa.isActive()) {
            throw new ApiException("Store Admin is not active");}
        if(!s.isActive()){
            throw new ApiException("Store is not active");}
        rating.setStore(s);
        rating.setExpert(e);
        double avr = (rating.getCleaning()+rating.getCost()+rating.getQuality()+rating.getService()) / 4;
        rating.setAverageRating(avr);
        expertRepository.save(e);
        ratingRepository.save(rating);}


    public void updateRating(Rating rating,Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating not found");}
        r.setService(rating.getService());
        r.setCleaning(rating.getCleaning());
        r.setQuality(rating.getQuality());
        r.setCost(rating.getCost());
        r.setComment(rating.getComment());
        r.setTitle(rating.getTitle());
        r.setAverageRating(rating.getAverageRating());
        ratingRepository.save(r);}



    public void deleteRating(Integer id) {
        Rating r = ratingRepository.findRatingById(id);
        if(r == null) {
            throw new ApiException("Rating not found");}
        ratingRepository.delete(r);
    }

    ///v2

    public double CalculateAverageRatingExpert(Integer expertId) {
        Expert expert = expertRepository.findExpertById(expertId);
        if (expert == null) {
            throw new ApiException("Expert not found");
        }
        List<Rating> ratings = ratingRepository.findRatingByExpert(expert);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given expert");
        }
        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getAverageRating();
        }

        return total / ratings.size();
    }
    public double CalculateAverageStore(Integer storeId) {
        Store store =storeRepository.findStoreById(storeId);
        if (store == null) {
            throw new ApiException("store not found");
        }

        List<Rating> ratings = ratingRepository.findRatingByStore(store);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given store");
        }

        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getAverageRating();
        }

        return total / ratings.size();
    }
    ///v2
    public double CalculateAverageQualityStore(Integer storeId) {
        Store store =storeRepository.findStoreById(storeId);
        if (store == null) {
            throw new ApiException("store not found");
        }
        List<Rating> ratings = ratingRepository.findRatingByStore(store);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given store");
        }
        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getQuality();
        }
        return total / ratings.size();
    }


    public double CalculateAverageServiceStore(Integer storeId) {
        Store store =storeRepository.findStoreById(storeId);
        if (store == null) {
            throw new ApiException("Store not found");
        }
        List<Rating> ratings = ratingRepository.findRatingByStore(store);
        if (ratings.isEmpty()) {
            throw new ApiException("No ratings found for the given store");
        }
        double total = 0;
        for (Rating rating : ratings) {
            total += rating.getService();}
        return total / ratings.size();
    }


    //V3
    public List<Store> getTop4CafesByAverageRating() {
        List<Store> allStores = storeRepository.findAll();
        List<Store> cafeList = new ArrayList<>();
        for (Store store : allStores) {
            if (store.getTypeOfActivity().equals("CAFE")) {
                cafeList.add(store);
            }}
        List<Store> bestTop4Cafes = new ArrayList<>();
        while (bestTop4Cafes.size() < 4 && !cafeList.isEmpty()) {
            Store bestCafe = cafeList.get(0);
            for (Store store : cafeList) {
                if (CalculateAverageStore(store.getId()) > CalculateAverageStore(bestCafe.getId())) {
                    bestCafe = store;}}
            bestTop4Cafes.add(bestCafe);
            cafeList.remove(bestCafe);}
        return bestTop4Cafes;}


    //V3
    public List<Store> getTop4RestaurantByAverageRating() {
        List<Store> allStores = storeRepository.findAll();
        List<Store> restaurantList = new ArrayList<>();
        for (Store store : allStores) {
            if (store.getTypeOfActivity().equals("RESTAURANT")) {
                restaurantList.add(store);
            }}
        List<Store> bestTop4Restaurant = new ArrayList<>();
        while (bestTop4Restaurant.size() < 4 && !restaurantList.isEmpty()) {
            Store bestRestaurant = restaurantList.get(0);
            for (Store store : restaurantList) {
                if (CalculateAverageStore(store.getId()) > CalculateAverageStore(bestRestaurant.getId())) {
                    bestRestaurant = store;}}
            bestTop4Restaurant.add(bestRestaurant);
            restaurantList.remove(bestRestaurant);}
        return bestTop4Restaurant;}


}
