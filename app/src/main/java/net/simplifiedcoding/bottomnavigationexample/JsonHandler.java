package net.simplifiedcoding.bottomnavigationexample;

import android.os.Environment;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    private String env=Environment.getExternalStorageDirectory().toString();

    public List<ItemAppointment> readAppointment(){
        List<ItemAppointment> memberList = new ArrayList<>();
        try {
            String filePath= env+ "/appointments.json";
            File file=new File(filePath);
            InputStream fileInputStream = new FileInputStream(file);
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
                memberList.add(new ItemAppointment(new_id,new_time,new_doctor,new_location));
                jsonReader.endObject();
            }
            jsonReader.endArray();
            jsonReader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return memberList;
    }

    public void writeAppointment(ItemAppointment item){
        writeAppointment(item,true);
    }

    public void writeAppointment(ItemAppointment item, Boolean isAdd){
        List<ItemAppointment> curr_appointment=readAppointment();
        if(isAdd){
            curr_appointment.add(item);
        }else {
            int index=-1;
            for(int i=0; i<curr_appointment.size();i++){
                if(curr_appointment.get(i).getId()==item.getId()){
                    index=i;
                }
            }
            if(index!=-1){
                curr_appointment.remove(index);
            }
        }
    }

    public void initJsonFile(){
        List<ItemAppointment> memberList = new ArrayList<>();
        String filePath = Environment.getExternalStorageDirectory().toString()
                + "/appointments.json";
        System.out.println(filePath);
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        memberList.add(new ItemAppointment(1904051001,"15:30 April 5, 2019","Aydin Matthews","Naneviet Hospital, 2586 Essendene Avenue, Abbotsford, British Columbia"));
        memberList.add(new ItemAppointment(1904251001,"d5:30 April 5, 2019","Aydin Matthews","Naneviet Hospital, 2586 Essendene Avenue, Abbotsford, British Columbia"));
        memberList.add(new ItemAppointment(190351001,"45:30 April 5, 2019","Aydin Matthews","Naneviet Hospital, 2586 Essendene Avenue, Abbotsford, British Columbia"));
        memberList.add(new ItemAppointment(190451001,"f5:30 April 5, 2019","Aydin Matthews","Naneviet Hospital, 2586 Essendene Avenue, Abbotsford, British Columbia"));
        System.out.println(memberList);
        JSONArray array = null;
        try {
            array = new JSONArray(memberList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        File file = new File(filePath);
        BufferedWriter output = null;
        try {
            System.out.println(file.exists());
            if(!file.exists()){
                file.createNewFile();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(array.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
