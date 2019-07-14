package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class Appointment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_appointment, null);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button) getActivity().findViewById(R.id.testbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
                ((MainActivity)getActivity()).loadFragment(new AppointmentInfo());
            }
        });

        List<ItemAppointment> memberList = new ArrayList<>();
        memberList.add(new ItemAppointment(1, "baishatunbeach1", "baishatunbeach1", "白沙屯海灘1"));
        memberList.add(new ItemAppointment(2, "baishatunbeach2", "baishatunbeach1", "白沙屯海灘2"));
        memberList.add(new ItemAppointment(3, "baishatunbeach3", "baishatunbeach1", "白沙屯海灘3"));
        memberList.add(new ItemAppointment(4, "baishatunbeach4", "baishatunbeach1", "白沙屯海灘4"));
        memberList.add(new ItemAppointment(5, "baishatunbeach5", "baishatunbeach1", "白沙屯海灘5"));
        memberList.add(new ItemAppointment(6, "baishatunspot3", "baishatunbeach1", "白沙屯3"));
        memberList.add(new ItemAppointment(7, "houlongthecape1","baishatunbeach1",  "後龍1"));
        memberList.add(new ItemAppointment(8, "houlongthecape2","baishatunbeach1",  "後龍2"));
        memberList.add(new ItemAppointment(9, "houlongthecape3","baishatunbeach1",  "後龍3"));

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MemberAdapter(getActivity(), memberList));
    }

}
