package com.openclassrooms.entrevoisins.ui.favneighbour_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.profile.ProfileActivity;
import com.openclassrooms.entrevoisins.utils.ItemClickSupport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class FavNeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mFavNeighbours;
    private RecyclerView mRecyclerView;

    public static FavNeighbourFragment newInstance() {
        FavNeighbourFragment fragment = new FavNeighbourFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_neighbours_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private void initList() {
        mFavNeighbours = mApiService.getFavouriteNeighbours();
        mRecyclerView.setAdapter(new com.openclassrooms.entrevoisins.ui.favneighbour_list.FavNeighbourAdapter(mFavNeighbours));
        if (mFavNeighbours!=null) {
            configureOnClickRecyclerView();
        }
    }

    /**
     * configure onCLick recyclerView
     */
    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(mRecyclerView)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        Neighbour neighbour = mFavNeighbours.get(position);
                        Context context = v.getContext();
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.putExtra("neighbour", neighbour);
                        context.startActivity(intent);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     *
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }

}
