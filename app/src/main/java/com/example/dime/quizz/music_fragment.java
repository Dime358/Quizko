package com.example.dime.quizz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Dime on 25-Mar-17.
 */

public class music_fragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.music_fragment, container, false);

        Button sport_btn = (Button) v.findViewById(R.id.btn_music);
        sport_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),playerScreen.class);
                intent.putExtra("categoryName","music");
                startActivity(intent);
            }
        });

        return v;
    }

}
