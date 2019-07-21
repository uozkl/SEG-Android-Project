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

import java.util.List;

/**
 * Created by Anbo.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{
    private CardView cardview1,cardview2;
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

        JsonHandler jsonHandler=new JsonHandler();

        List<ItemAppointment> appList =jsonHandler.readAppointment();
        if(appList.size()>0){
            ItemAppointment currAppointment= jsonHandler.readAppointment().get(0);
            String[] curr_address=currAppointment.getLocation().split(",");
            displayAppointment.setText(currAppointment.getTime()+"\nDoctor "+currAppointment.getDoctor()+"\n"+curr_address[0]);
        }


        if(((MainActivity)getActivity()).getHospital().equals("")){
            displayQueueInfo.setText("Currently no number in queue");
        }
        else{
            displayQueueInfo.setText(((MainActivity)getActivity()).getHospital()
            +"\nCurrent Number: "+((MainActivity)getActivity()).getCurrentNumber()
            +"\nYour Number: "+((MainActivity)getActivity()).getMyNumber());
        }
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        //on activity create event handler
        super.onActivityCreated(savedInstanceState);
        cardview1=(CardView)getActivity().findViewById(R.id.homeCardView);
        cardview2=(CardView)getActivity().findViewById(R.id.appointCardView);
        cardview1.setOnClickListener(this);
        cardview2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //onclick event handler
        switch (view.getId()){
            case R.id.appointCardView : ((MainActivity)getActivity()).loadFragment(new Appointment());break;
            case R.id.homeCardView : ((MainActivity)getActivity()).loadFragment(new Queue());break;
            default:break;
        }
    }
}
