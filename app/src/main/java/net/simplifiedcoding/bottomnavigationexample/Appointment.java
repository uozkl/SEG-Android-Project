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
 * Created by Belal on 1/23/2018.
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

        super.onActivityCreated(savedInstanceState);
        final RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Button button = (Button) getActivity().findViewById(R.id.testbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                // ((MainActivity)getActivity()).loadFragment(new AppointmentInfo());

            }
        });

        try {
            InputStream fileInputStream = getActivity().getAssets().open("appointments.json");
            @SuppressWarnings("resource")
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream, "UTF-8"));
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                long new_id=-1;
                String new_doctor =null;
                String new_location =null;
                String new_time =null;
                while (jsonReader.hasNext()) {
                    String name = jsonReader.nextName();
                    if (name.equals("location")) {
                        new_location=jsonReader.nextString();
                    }
                    if (name.equals("doctor")) {
                        new_doctor=jsonReader.nextString();
                    }
                    if (name.equals("id")) {
                        new_id=jsonReader.nextLong();
                    }
                    if (name.equals("time")) {
                        new_time=jsonReader.nextString();
                    }
                }
                System.out.println((new_location+new_time)+new_doctor);
                memberList.add(new ItemAppointment(new_id,new_time,new_doctor,new_location));
                jsonReader.endObject();
            }
            jsonReader.endArray();
            jsonReader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        recyclerView.setAdapter(new MemberAdapter(((MainActivity)getActivity()), memberList));

    }

}
