package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;


/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addToFavouriteNeighboursWithSuccess() {
        Neighbour neighbourToAddToFavourite = service.getNeighbours().get(0);
        service.addToFavouriteNeighbours(neighbourToAddToFavourite);
        assertEquals(true, neighbourToAddToFavourite.getIsFavourite());
    }

    @Test
    public void getFavouriteNeighboursWithSuccess() {
        Neighbour favouriteNeighbour = service.getNeighbours().get(0);
        service.addToFavouriteNeighbours(favouriteNeighbour);
        assertEquals(1, service.getFavouriteNeighbours().size());
    }

    @Test
    public void removeFavouriteNeighbourWithSuccess() {
        Neighbour favouriteNeighbour = service.getNeighbours().get(0);
        service.addToFavouriteNeighbours(favouriteNeighbour);

        service.removeFromFavouriteNeighbours(favouriteNeighbour);
        assertEquals(false, favouriteNeighbour.getIsFavourite());
    }

    @Test
    public void deleteFavouriteNeighbourWithSuccess() {
        Neighbour neighbourToAddToFavourite = service.getNeighbours().get(0);
        service.addToFavouriteNeighbours(neighbourToAddToFavourite);

        Neighbour favouriteNeighbourToDelete = service.getFavouriteNeighbours().get(0);
        service.deleteNeighbour(favouriteNeighbourToDelete);

        List<Neighbour> FavouriteNeighbours = service.getFavouriteNeighbours();
        assertEquals(0, FavouriteNeighbours.size());
    }


}
