package com.openclassrooms.entrevoisins.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {

    private NeighbourApiService mApiService;
 ////////// UI COMPONENTS ///////////
    @BindView(R.id.imgUser)
    ImageView mAvatar;
    @BindView(R.id.title_profile)
    TextView mTitle;
    @BindView(R.id.titleName_profile)
    TextView mName;
    @BindView(R.id.address_profile)
    TextView mAddress;
    @BindView(R.id.phoneNumber_profile)
    TextView mPhoneNumber;
    @BindView(R.id.Url_profile)
    TextView mUrl;
    @BindView(R.id.aboutMe_profile)
    TextView mAboutMe;
    @BindView(R.id.favFab)
    FloatingActionButton mFavourite;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleBarStyle();
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

///////// RECOVERING DATA /////////////
        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("neighbour");

        String name = neighbour.getName();
        String address = neighbour.getAddress();
        String phone = neighbour.getPhoneNumber();
        String avatar = neighbour.getAvatarUrl();
        String aboutMe = neighbour.getAboutMe();

        RequestOptions myOptions = new RequestOptions()
                .centerCrop();
        Glide.with(this).load(avatar).apply(myOptions).timeout(60000).into(mAvatar);
        mTitle.setText(name);
        mName.setText(name);
        mAddress.setText(address);
        mPhoneNumber.setText(phone);
        mUrl.setText("www.facebook.com/" + name);
        mAboutMe.setText(aboutMe);
        setFabImage(neighbour);

////////// INTERACTION HANDLING/////////////////
        mFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFabClick(neighbour);
            }
        });
    }

    public void setFabImage(Neighbour neighbour) {
        if (!neighbour.getIsFavourite()) {
            mFavourite.setImageResource(R.drawable.ic_star_border_white_24dp);
        } else if (neighbour.getIsFavourite()) {
            mFavourite.setImageResource(R.drawable.ic_baseline_star_24);
        }
    }

    public void setFabClick(Neighbour neighbour) {

        if (!neighbour.getIsFavourite()) {
            mApiService.addToFavouriteNeighbours(neighbour);
            mFavourite.setImageResource(R.drawable.ic_baseline_star_24);
        } else if (neighbour.getIsFavourite()) {
            mApiService.removeFromFavouriteNeighbours(neighbour);
            mFavourite.setImageResource(R.drawable.ic_star_border_white_24dp);
        }
    }

    public void setTitleBarStyle() {
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getSupportActionBar().setTitle(" ");
    }
}
