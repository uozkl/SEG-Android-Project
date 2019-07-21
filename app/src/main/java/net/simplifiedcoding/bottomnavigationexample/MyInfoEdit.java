package net.simplifiedcoding.bottomnavigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

//Created by Anbo

//Created handler for edit function
public class MyInfoEdit extends Fragment {
    String newTel;
    EditText displayTel;
    EditText displayEmail;
    Button saveTel;
    Button saveEmail;
    Button cancel;
    @Nullable
    @Override
    //handler
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo_edit, null);
        displayTel = (EditText)view.findViewById(R.id.editText3);
        displayTel.setText(((MainActivity)getActivity()).getTel(), TextView.BufferType.EDITABLE);
        saveTel = (Button)view.findViewById(R.id.button);
        saveTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setTel(displayTel.getText().toString());
                Toast.makeText(getView().getContext(),"New tel number save success",Toast.LENGTH_SHORT).show();
            }
        });

        displayEmail = (EditText)view.findViewById(R.id.editText5);
        displayEmail.setText(((MainActivity)getActivity()).getEmail(), TextView.BufferType.EDITABLE);
        saveEmail =(Button)view.findViewById(R.id.button2);
        saveEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).setEmail(displayEmail.getText().toString());
                Toast.makeText(getView().getContext(),"New email set success",Toast.LENGTH_SHORT).show();
            }
        });

        cancel =(Button)view.findViewById(R.id.button_back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFragment(new Myinfo());
            }
        });
        return view;
    }

    //handler
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
