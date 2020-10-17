package com.openclassrooms.entrevoisins.ui.profille;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;

    @BindView(R.id.return_image) ImageView mReturn;
    @BindView(R.id.imgUser) ImageView mAvatar;
    @BindView(R.id.title_profile) TextView mTitle;
    @BindView(R.id.titleName_profile) TextView mName;
    @BindView(R.id.address_profile) TextView mAddress;
    @BindView(R.id.phoneNumber_profile) TextView mPhoneNumber;
    @BindView(R.id.Url_profile) TextView mUrl;
    @BindView(R.id.aboutMe_profile) TextView mAboutMe;
    @BindView(R.id.favFab) FloatingActionButton mFavourite;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("neighbour");

        String name = neighbour.getName();
        String address = neighbour.getAddress();
        String phone = neighbour.getPhoneNumber();
        String avatar = neighbour.getAvatarUrl();
        String aboutMe = neighbour.getAboutMe();

            Glide.with(this).load(avatar).override(470, 350).into(mAvatar);
            mTitle.setText(name);
            mName.setText(name);
            mAddress.setText(address);
            mPhoneNumber.setText(phone);
            mUrl.setText("www.facebook.com/" + name);
            mAboutMe.setText(aboutMe);

        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListNeighbourActivity = new Intent(ProfileActivity.this, com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.class);
                startActivity(ListNeighbourActivity);
            }
        });

        mFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApiService.addToFavouriteNeighbours(neighbour);
                if (neighbour.getIsFavourite()) {
                    mFavourite.setImageResource(R.drawable.ic_baseline_star_24);
                }
                else mFavourite.setImageResource(R.drawable.ic_star_border_white_24dp);

            }
        });
    }
}
