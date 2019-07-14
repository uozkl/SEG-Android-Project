package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Belal on 1/23/2018.
 */

public class AppointmentInfo extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        return inflater.inflate(R.layout.fragment_appointment_info, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ItemAppointment item=new JsonHandler().getAppointmentSelection();

        TextView t=new TextView(getActivity());

        t=(TextView)getActivity().findViewById(R.id.text_info_appono);
        t.setText(String.valueOf(item.getId()));

        t=(TextView)getActivity().findViewById(R.id.text_info_time);
        t.setText(item.getTime());

        t=(TextView)getActivity().findViewById(R.id.text_info_location);
        t.setText(item.getLocation());

        t=(TextView)getActivity().findViewById(R.id.text_info_doctor);
        t.setText(item.getDoctor());

        t=(TextView)getActivity().findViewById(R.id.text_info_tel);
        Random random=new Random();
        int p1=random.nextInt(899)+100;
        int p2=random.nextInt(899)+100;
        int p3=random.nextInt(8999)+1000;
        String tel=p1+"-"+p2+"-"+p3;
        t.setText(tel);




        final Button button_back = (Button) getActivity().findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new Appointment());
                // your handler code here
            }
        });
        final Button button_eidt = (Button) getActivity().findViewById(R.id.button_edit);
        button_eidt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new AppointmentEdit());
                // your handler code here
            }
        });

    }

}
