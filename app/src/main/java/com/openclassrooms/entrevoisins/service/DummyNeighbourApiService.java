package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> FAVOURITE_NEIGHBOURS = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
        FAVOURITE_NEIGHBOURS.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    @Override
    public void addToFavouriteNeighbours(Neighbour neighbour) {
        if (!neighbour.getIsFavourite()) {
            neighbour.setIsFavourite(true);
            FAVOURITE_NEIGHBOURS.add(neighbour);
        }
    }

    @Override
    public List<Neighbour> getFavouriteNeighbours() {
        return FAVOURITE_NEIGHBOURS;
    }
}
