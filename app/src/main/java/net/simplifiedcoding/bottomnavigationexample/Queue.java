package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;
import android.widget.Toast;
import java.util.ArrayList;


public class Queue extends Fragment implements SearchView.OnQueryTextListener{
    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    ArrayList<HospitalNames> arraylist = new ArrayList<HospitalNames>();
    TextView currentNumber;
    TextView queueNumber;
    Button takeNumberButton;
    Button leaveQueueButton;

    int randomNumber = -1;
    int nextNumber = -1;
    String selectHospital ="";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_queue, null);

        animalNameList = new String[]{"Riverside Hospital", "Ottawa Civic Hospital", "University Health Services",
                "Denture Clinic"};

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.listview);


        for (int i = 0; i < animalNameList.length; i++) {
            HospitalNames hospitalNames = new HospitalNames(animalNameList[i]);
            // Binds all strings into an array
            arraylist.add(hospitalNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this.getContext(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        currentNumber = (TextView) view.findViewById(R.id.textView5);
        queueNumber = (TextView) view.findViewById(R.id.textView);

        takeNumberButton = (Button) view.findViewById(R.id.button3);
        leaveQueueButton = (Button) view.findViewById(R.id.button4);
        takeNumberButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(selectHospital.equals("")||nextNumber==-1){
                    Toast.makeText(getView().getContext(),"Please select hospital",Toast.LENGTH_SHORT).show();
                }
                else{
                    queueNumber.setText(Integer.toString(nextNumber));
                    ((MainActivity)getActivity()).setQueue(selectHospital,Integer.toString(randomNumber),Integer.toString(nextNumber));
                }
            }
        });

        leaveQueueButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(selectHospital.equals("")||nextNumber==-1){
                    ((MainActivity)getActivity()).setQueue("","","");
                    Toast.makeText(getView().getContext(),"You do not have taken any number",Toast.LENGTH_SHORT).show();
                }
                else{
                    nextNumber = -1;
                    randomNumber = -1;
                    queueNumber.setText("--");
                    ((MainActivity)getActivity()).setQueue("","","");
                }
            }
        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                queueNumber.setText("--");
                selectHospital = adapter.getItem(i).getHospitalName();
                editsearch.setQuery(selectHospital, false);
                editsearch.clearFocus();
                Random rand = new Random();
                randomNumber = rand.nextInt(50)+1;
                nextNumber = randomNumber+rand.nextInt(15)+1;
                currentNumber.setText(Integer.toString(randomNumber));

            }
        });


        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(!((MainActivity)getActivity()).getHospital().equals("")){
            editsearch.setQuery(((MainActivity)getActivity()).getHospital(), false);
            editsearch.clearFocus();
            currentNumber.setText(((MainActivity)getActivity()).getCurrentNumber());
            queueNumber.setText(((MainActivity)getActivity()).getMyNumber());
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

}
