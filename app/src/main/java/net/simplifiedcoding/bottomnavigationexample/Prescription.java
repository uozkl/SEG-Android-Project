package net.simplifiedcoding.bottomnavigationexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zekun.
 */

public class Prescription extends Fragment implements View.OnClickListener{
    private CardView cardview1,cardview2,cardview3,cardview4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Show fragment
        return inflater.inflate(R.layout.fragment_prescription, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        //Button listener for four different cards
        super.onActivityCreated(savedInstanceState);

        cardview1=(CardView)getActivity().findViewById(R.id.card1);
        cardview2=(CardView)getActivity().findViewById(R.id.card2);
        cardview3=(CardView)getActivity().findViewById(R.id.card3);
        cardview4=(CardView)getActivity().findViewById(R.id.card4);

        cardview1.setOnClickListener(this);
        cardview2.setOnClickListener(this);
        cardview3.setOnClickListener(this);
        cardview4.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){
        //card onclick event
        switch (v.getId()){
            case R.id.card1 : ((MainActivity)getActivity()).loadFragment(new PrescriptionInfo1());break;
            case R.id.card2 : ((MainActivity)getActivity()).loadFragment(new PrescriptionInfo2());break;
            case R.id.card3 : ((MainActivity)getActivity()).loadFragment(new PrescriptionInfo3());break;
            case R.id.card4 : ((MainActivity)getActivity()).loadFragment(new PrescriptionInfo4());break;
            default:break;
        }
    }


}
