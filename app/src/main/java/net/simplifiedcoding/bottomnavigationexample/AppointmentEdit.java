package net.simplifiedcoding.bottomnavigationexample;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zekun.
 */

public class AppointmentEdit extends Fragment {

    private static final String [] year ={"2019","2020","2021"};
    private static final String [] month ={"January","February","March","April","May","June","July","August","September","October","November","December"};
    private static final String [] date ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
    private static final String [] hour ={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    private static final String [] minute ={"00","15","30","45"};
    private static final String [] doctors={"Matt Murphy"
            ,"Kerri Morton"
            ,"Antonio Kerr"
            ,"Javan Flowers"
            ,"Rhianna Vincent"
            ,"Archer Hines"
            ,"Wyatt Benjamin"
            ,"Veronica Lennon"
            ,"Giselle Doherty"
            ,"Divine Meyers"
            ,"Tamika Robles"
            ,"Fariha Bernal"
            ,"Sabiha Riggs"
            ,"Shaquille Dunlop"
            ,"Laurence Berry"
            ,"Olivia-Mae Houston"
            ,"Gurdeep Payne"
            ,"Effie Nieves"
            ,"Tilly Mcintyre"
            ,"Maciej Trejo"
            ,"Aydin Matthews"
            ,"Gregory Frank"
            ,"Nakita Browne"
            ,"Esmay Pennington"
            ,"Oskar Rush"
    };
    private static String[] hospitals={
            "Naneviet Hospital",
            "Quapia Hospital",
            "Landra Hospital",
            "Blicda Hospital",
            "Neako Hospital",
            "New Merengefort Hospital",
            "La Rihardiacre Hospital",
            "West Thtonvilsault Hospital",
            "Great Tickbed Hospital",
            "New Caha Hospital",
            "Prince Sandsshilge Hospital",
            "Forksharmouth Hospital",
            "Northpag Hospital",
            "Hitheweileine Hospital",
            "Niarnard Hospital",
            "South Temat Hospital"
    };
    private static final String[] address={
            "2586 Essendene Avenue, Abbotsford, British Columbia",
            "3861 rue Parc, Sherbrooke, Quebec",
            "4996 rue des Champs, Chicoutimi, Quebec",
            "3761 Runnymede Rd, Toronto, Ontario",
            "2031 Scotchmere Dr, Sarnia, Ontario",
            "197 Millard Ave, Newmarket",
            "7770 Burris St, Burnaby, British Columbia",
            "6013 Ave Freud, Cote Saint Luc, Quebec",
            "2034 Pleasant Bluff Circuit, Chili, Quebec",
            "4663 Misty Pike, Saint Marys, Ontario",
            "3043 Grand Autumn Close, Glen Buell, Ontario",
            "9886 Hazy Bay, Key Junction, Ontario"
    };
    ItemAppointment item=new JsonHandler().getAppointmentSelection();
    Spinner spinner_y =null;
    Spinner spinner_m =null;
    Spinner spinner_d =null;
    Spinner spinner_hrs=null;
    Spinner spinner_min=null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Display fragment
        return inflater.inflate(R.layout.fragment_appointment_edit, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        //Add listener to the button
        //Load list for all the spinners
        //Add hint for autocomplete textview
        super.onActivityCreated(savedInstanceState);
        final Button button_back = (Button) getActivity().findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).loadFragment(new AppointmentInfo());
            }
        });
        final Button button_submit = (Button) getActivity().findViewById(R.id.button_submit);
        button_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSubmitButtonPressed();
            }
        });
        final Button button_cancel = (Button) getActivity().findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onCancelButtonPressed();
            }
        });


        spinner_y = (Spinner) getActivity().findViewById(R.id.spinner_year);
        spinner_m = (Spinner) getActivity().findViewById(R.id.spinner_month);
        spinner_d = (Spinner) getActivity().findViewById(R.id.spinner_date);
        spinner_hrs = (Spinner) getActivity().findViewById(R.id.spinner_hrs);
        spinner_min = (Spinner) getActivity().findViewById(R.id.spinner_min);
        setSpinner(spinner_y,year);
        setSpinner(spinner_m,month);
        setSpinner(spinner_d,date);
        setSpinner(spinner_hrs,hour);
        setSpinner(spinner_min,minute);

        String appo_id=String.valueOf(item.getId());
        int year_index=Integer.parseInt(appo_id.substring(0,2))-19;
        int month_index=Integer.parseInt(appo_id.substring(2,4))-1;
        int day_index=Integer.parseInt(appo_id.substring(4,6))-1;
        int hrd_index=Integer.parseInt(appo_id.substring(6,8))-1;
        int min_index=Integer.parseInt(appo_id.substring(8,10))/15;

        spinner_y.setSelection(year_index);
        spinner_m.setSelection(month_index);
        spinner_d.setSelection(day_index);
        spinner_hrs.setSelection(hrd_index);
        spinner_min.setSelection(min_index);

        AutoCompleteTextView nameText = (AutoCompleteTextView) getActivity().findViewById(R.id.text_info_doctor);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, doctors);
        nameText.setAdapter(adapter);

        TextView t=new TextView(getActivity());
        t=(AutoCompleteTextView)getActivity().findViewById(R.id.text_info_doctor);
        t.setText(item.getDoctor());




        final AutoCompleteTextView location=(AutoCompleteTextView)getActivity().findViewById(R.id.text_info_location);
        final ArrayAdapter<String> location_adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, hospitals);
        location.setAdapter(location_adapter);





        location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                location_adapter.getFilter().filter(s);
            }
            @Override
            public void afterTextChanged(Editable s) {
                TextView t=(TextView)getActivity().findViewById(R.id.text_info_address);
                t.setText(address[new Random().nextInt(address.length)]);
            }
        });
        final String[] curr_address=item.getLocation().split(",");
        location.setText(curr_address[0]);
        t=(TextView)getActivity().findViewById(R.id.text_info_address);
        t.setText((curr_address[1]+", "+curr_address[2]+", "+curr_address[3]));


    }

    private void setSpinner(Spinner spinner,String[] list){
        // Set the list in the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onSubmitButtonPressed() {
        //Listener of the submit button. Save all the selection to the json
        new AlertDialog.Builder(getActivity()).setTitle("Do you want to submit this form?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        JsonHandler jsonHandler=new JsonHandler();
                        ItemAppointment itemAppointment=new ItemAppointment();
                        TextView textView=new TextView(getActivity());
                        textView=(AutoCompleteTextView)getActivity().findViewById(R.id.text_info_doctor);
                        String doc_string= textView.getText().toString();
                        textView=(AutoCompleteTextView)getActivity().findViewById(R.id.text_info_location);
                        String location_string=textView.getText().toString();
                        textView=(TextView)getActivity().findViewById(R.id.text_info_address);
                        String address_string=textView.getText().toString();
                        String time_string= genTime();
                        long id_long=genId();
                        ItemAppointment new_item=new ItemAppointment(id_long,time_string,doc_string,location_string+", "+address_string);
                        jsonHandler.removeAppointmentById(String.valueOf(item.getId()));
                        jsonHandler.addAppointment(new_item);
                        ((MainActivity)getActivity()).loadFragment(new Appointment());
                        Toast.makeText(getActivity(), "Appointment updated ", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
    public void onCancelButtonPressed() {
        //Listener of the cancel button. Delete this appointment from JSON
        new AlertDialog.Builder(getActivity()).setTitle("Do you want to cancel this appointment?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new JsonHandler().removeAppointmentById(String.valueOf(item.getId()));
                        ((MainActivity)getActivity()).loadFragment(new Appointment());
                        Toast.makeText(getActivity(), "Appointment Canceled", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    public long genId(){
        //Generate appointment id from current selection
        String year,month,date,hour,minute,ran;
        year=spinner_y.getSelectedItem().toString().substring(2,4);
        month=String.valueOf(spinner_m.getSelectedItemId()+1);
        if(month.length()==1){
            month="0"+month;
        }
        date=spinner_d.getSelectedItem().toString();
        if(date.length()==1){
            date="0"+date;
        }
        hour=spinner_hrs.getSelectedItem().toString();
        if(hour.length()==1){
            hour="0"+hour;
        }
        minute=spinner_min.getSelectedItem().toString();
        ran=String.valueOf(new Random().nextInt(89)+10);
        String resId=year+month+date+hour+minute+ran;
        return Long.parseLong(resId);
    }

    public String genTime(){
        //Generate appointment time from current selection
        String year,month,date,hour,minute;
        year=spinner_y.getSelectedItem().toString();
        month=spinner_m.getSelectedItem().toString();
        date=spinner_d.getSelectedItem().toString();
        hour=spinner_hrs.getSelectedItem().toString();
        minute=spinner_min.getSelectedItem().toString();
        return hour+":"+minute+" "+month+" "+date+", "+year;
    }

}
