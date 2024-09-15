package com.example.Thawaq.Service;


import com.example.Thawaq.Api.ApiException;
import com.example.Thawaq.Model.Favorite;
import com.example.Thawaq.Model.Menu;
import com.example.Thawaq.Repository.FavoriteRepository;
import com.example.Thawaq.Repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final MenuRepository menuRepository;

    public void AddFavorite(Favorite favorite, Integer MenuId) {
        Menu menu = menuRepository.findMenuById(MenuId);
        if (menu==null){
            throw new ApiException("can not add favorite");
        }
        favorite.setMenu(menu);
        favoriteRepository.save(favorite);
    }

    public void DeleteFavorite(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findFavoriteById(favoriteId);
        if (favorite ==null){
            throw new ApiException("can not delete favorite");
        }
        favoriteRepository.delete(favorite);

    }


}