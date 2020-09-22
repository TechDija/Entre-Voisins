package com.openclassrooms.entrevoisins;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private ImageView mReturn;
    private ImageView mAvatar;
    private TextView mTitle;
    private TextView mName;
    private TextView mAddress;
    private TextView mPhoneNumber;
    private TextView mUrl;
    private TextView mAboutMe;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra("neighbour");

        String name = neighbour.getName();
        String address = neighbour.getAddress();
        String phone = neighbour.getPhoneNumber();
        String avatar = neighbour.getAvatarUrl();
        String aboutMe = neighbour.getAboutMe();


        mReturn = (ImageView) findViewById(R.id.return_image);
        mAvatar = (ImageView) findViewById(R.id.imgUser);
            Picasso.get().load(avatar).resize(470, 300).into(mAvatar);
        mTitle = (TextView) findViewById(R.id.title_profile);
            mTitle.setText(name);
        mName = (TextView) findViewById(R.id.titleName_profile);
            mName.setText(name);
        mAddress = (TextView) findViewById(R.id.address_profile);
            mAddress.setText(address);
        mPhoneNumber = (TextView) findViewById(R.id.phoneNumber_profile);
            mPhoneNumber.setText(phone);
        mUrl = (TextView) findViewById(R.id.Url_profile);
            mUrl.setText("www.facebook.com/" + name);
        mAboutMe = (TextView) findViewById(R.id.aboutMe_profile);
            mAboutMe.setText(aboutMe);

        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ListNeighbourActivity = new Intent(ProfileActivity.this, com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity.class);
                startActivity(ListNeighbourActivity);
            }
        });
    }
}