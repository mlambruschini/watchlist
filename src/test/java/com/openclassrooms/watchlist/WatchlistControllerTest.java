package com.openclassrooms.watchlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
@RunWith(SpringRunner.class)
public class WatchlistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testShowWatchListItemForm() throws Exception {
        mockMvc.perform(get("/watchlistitemform"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("watchListItemForm"))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("watchListItem"));
    }

    @Test
    public void testSubmitWatchListItemForm() throws Exception {
        mockMvc.perform(post("/watchlistitemform"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/watchlist"));
    }
}
