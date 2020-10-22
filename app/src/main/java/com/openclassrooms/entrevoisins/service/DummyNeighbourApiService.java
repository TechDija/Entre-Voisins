package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

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

    public void removeFromFavouriteNeighbours(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setIsFavourite(false);
    }

    public boolean getFavourite(Neighbour neighbour) {
       return neighbours.get(neighbours.indexOf(neighbour)).getIsFavourite();
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
