package net.simplifiedcoding.bottomnavigationexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class Myinfo extends Fragment {
    TextView emailText;
    TextView telText;
    Button editInfo;
    Button logout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo, null);
        emailText = (TextView) view.findViewById(R.id.textView42);
        emailText.setText(((MainActivity)getActivity()).getEmail());
        telText = (TextView) view.findViewById(R.id.textView44);
        telText.setText(((MainActivity)getActivity()).getTel());
        editInfo = (Button) view.findViewById(R.id.editInfoButton);
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loadFragment(new MyInfoEdit());
            }
        });
        logout = (Button)view.findViewById(R.id.editInfoButton2);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Signin.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        emailText.setText(((MainActivity)getActivity()).getEmail());
        telText.setText(((MainActivity)getActivity()).getTel());
    }
}
