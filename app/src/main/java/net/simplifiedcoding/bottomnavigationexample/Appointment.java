package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zekun.
 */

public class Appointment extends Fragment {
    List<ItemAppointment> memberList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        //Generate the list in the recycler view
        //Add listener to the button

        super.onActivityCreated(savedInstanceState);
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        memberList=new JsonHandler().readAppointment();
        recyclerView.setAdapter(new MemberAdapter(((MainActivity)getActivity()), memberList));

        final Button button_back = (Button) getActivity().findViewById(R.id.button_add);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new AppointmentAdd());
                // your handler code here
            }
        });
    }

}
