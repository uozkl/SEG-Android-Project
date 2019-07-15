package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.RelativeLayout;

/**
 * Created by Belal on 1/23/2018.
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view =  inflater.inflate(R.layout.fragment_home, null);
        TextView displayQueueInfo = view.findViewById(R.id.textView29);
        TextView displayAppointment = view.findViewById(R.id.appointTextView1);
        Button editAppointment = view.findViewById(R.id.appointButton);
        Button editQueue = view.findViewById(R.id.button5);
        displayAppointment.setText("Appointment#"+new JsonHandler().readAppointment().get(0).getId()+"\n"+
                "Doctor: "+new JsonHandler().readAppointment().get(0).getDoctor()+"\n"+
                "Time: "+new JsonHandler().readAppointment().get(0).getTime());
        editQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFragment(new Queue());
            }
        });
        editAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFragment(new AppointmentEdit());
            }
        });
        if(((MainActivity)getActivity()).getHospital().equals("")){
            displayQueueInfo.setText("Currently no number in queue");
        }
        else{
            displayQueueInfo.setText(((MainActivity)getActivity()).getHospital()
            +"\n Current Number:"+((MainActivity)getActivity()).getCurrentNumber()
            +"\n Your Number:"+((MainActivity)getActivity()).getMyNumber());
        }
        return view;
    }
}
