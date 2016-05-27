package com.abhilashmishra.playmenttest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {


    ImageView im1,im2,im3;
    public Fragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);

        if(savedInstanceState==null) {
            im1 = (ImageView) view.findViewById(R.id.button1);
            im2 = (ImageView) view.findViewById(R.id.button2);
            im3 = (ImageView) view.findViewById(R.id.button3);

            im1.setAlpha(0f);
            im2.setAlpha(0f);
            im3.setAlpha(0f);

            im1.animate().alpha(1f)
                    .setDuration(500)
                    .setStartDelay(500)
                    .start();

            im2.animate().alpha(1f)
                    .setDuration(500)
                    .setStartDelay(1000)
                    .start();

            im3.animate().alpha(1f)
                    .setDuration(500)
                    .setStartDelay(1500)
                    .start();
        }
        return view;
    }

}
