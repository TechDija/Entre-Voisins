package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

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
        neighbours.get(neighbours.indexOf(neighbour)).setIsFavourite(true);
    }

    @Override
    public void removeFromFavouriteNeighbours(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setIsFavourite(false);
    }

    @Override
    public List<Neighbour> getFavouriteNeighbours() {
        List<Neighbour> favouriteNeighbours = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.getIsFavourite()) {
                favouriteNeighbours.add(neighbour);
            }
        }
        return favouriteNeighbours;
    }
}
