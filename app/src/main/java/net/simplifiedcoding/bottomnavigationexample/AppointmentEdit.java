package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Belal on 1/23/2018.
 */

public class AppointmentEdit extends Fragment {

    private static final String [] year ={"2019","2020","2021"};
    private static final String [] month ={"January","February","March","April","May","June","July","August","September","October","November","December"};
    private static final String [] date ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
    private static final String [] hour ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
    private static final String [] minute ={"00","15","30","45"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment_edit, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Button button_back = (Button) getActivity().findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new Appointment());
                // your handler code here
            }
        });

        ItemAppointment item=new JsonHandler().getAppointmentSelection();

        TextView t=new TextView(getActivity());



        t=(TextView)getActivity().findViewById(R.id.text_info_location);
        t.setText(item.getLocation());

        t=(TextView)getActivity().findViewById(R.id.text_info_doctor);
        t.setText(item.getDoctor());

        Spinner spinner_y = (Spinner) getActivity().findViewById(R.id.spinner_year);
        Spinner spinner_m = (Spinner) getActivity().findViewById(R.id.spinner_month);
        Spinner spinner_d = (Spinner) getActivity().findViewById(R.id.spinner_date);
        Spinner spinner_hrs = (Spinner) getActivity().findViewById(R.id.spinner_hrs);
        Spinner spinner_min = (Spinner) getActivity().findViewById(R.id.spinner_min);
        setSpinner(spinner_y,year);
        setSpinner(spinner_m,month);
        setSpinner(spinner_d,date);
        setSpinner(spinner_hrs,hour);
        setSpinner(spinner_min,minute);

        spinner_y.setSelection(1);



    }

    private void setSpinner(Spinner spinner,String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setSpinnerDefaultPosition(){

    }

}
