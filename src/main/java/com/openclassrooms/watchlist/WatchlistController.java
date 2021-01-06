package com.openclassrooms.watchlist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private List<WatchlistItem> watchListItems = new ArrayList<WatchlistItem>();
    private static int index = 1;

    @GetMapping("/watchlistitemform")
    public ModelAndView showWatchListItemForm(@RequestParam(required = false) Integer id){
        String viewName = "watchListItemForm";
        Map<String,Object> model = new HashMap<>();
        WatchlistItem watchlistItem = findWatchListItemById(id);
        if (watchlistItem == null){
            model.put("watchListItem", new WatchlistItem());
        } else {
            model.put("watchListItem", watchlistItem);
        }
        return new ModelAndView(viewName, model);
    }

    private WatchlistItem findWatchListItemById(Integer id){
        for (WatchlistItem watchlistItem : watchListItems){
            if (watchlistItem.getId().equals(id)){
                return watchlistItem;
            }
        }
        return null;
    }

    @PostMapping("/watchlistitemform")
    public ModelAndView submitWatchListItemForm(WatchlistItem watchlistItem){

        WatchlistItem existingItem = findWatchListItemById(watchlistItem.getId());
        if (existingItem == null){
            watchlistItem.setId(index++);
            watchListItems.add(watchlistItem);
        } else {
            existingItem.setComment(watchlistItem.getComment());
            existingItem.setPriority(watchlistItem.getPriority());
            existingItem.setRating(watchlistItem.getRating());
            existingItem.setTitle((watchlistItem.getTitle()));
        }

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/watchlist");
        return new ModelAndView(redirect);
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist(){
        String viewName = "watchlist";

        Map<String, Object> model = new HashMap<>();
        model.put("watchListItems", watchListItems);
        model.put("numberOfMovies", watchListItems.size());
        return new ModelAndView(viewName, model);
    }
}
