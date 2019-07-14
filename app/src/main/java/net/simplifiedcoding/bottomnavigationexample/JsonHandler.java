package net.simplifiedcoding.bottomnavigationexample;

import android.os.Environment;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
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

    public void addAppointment(ItemAppointment item){
        writeAppointment(item,true);
    }

    public void removeAppointmentById(String id){
        long item_id=Long.parseLong(id);
        writeAppointment(new ItemAppointment(item_id,"","",""),false);

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
        File file = new File(env+ "/appointments.json");
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
            output.write(curr_appointment.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeAppointmentById(Long id){
    }


    public void initJsonFile(){
        List<ItemAppointment> memberList = new ArrayList<>();
        String filePath = env + "/appointments.json";
        memberList.add(new ItemAppointment(Long.parseLong("190405153001"),"15:30 April 5, 2019","Aydin Matthews","Naneviet Hospital, 2586 Essendene Avenue, Abbotsford, British Columbia"));
        memberList.add(new ItemAppointment(Long.parseLong("190415093057"),"9:30 April 15, 2019","Gregory Frank","Quapia Hospital, 3861 rue Parc, Sherbrooke, Quebec"));
        memberList.add(new ItemAppointment(Long.parseLong("190512110032"),"11:00 May 12, 2019","Nakita Browne","Landra Hospital, 4996 rue des Champs, Chicoutimi, Quebec"));
        memberList.add(new ItemAppointment(Long.parseLong("190905160024"),"16:00  September 5, 2019","Esmay Pennington","Blicda Hospital, 3761 Runnymede Rd, Toronto, Ontario"));
        memberList.add(new ItemAppointment(Long.parseLong("190409153026"),"15:30 April 9, 2019","Oskar Rush","Neako Hospital, 2031 Scotchmere Dr, Sarnia, Ontario"));
        JSONArray array = null;
        try {
            array = new JSONArray(memberList.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        File appointmentFile = new File(filePath);
        BufferedWriter output = null;
        try {
            System.out.println(appointmentFile.exists());
            if(!appointmentFile.exists()){
                appointmentFile.createNewFile();}
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output = new BufferedWriter(new FileWriter(appointmentFile));
            output.write(array.toString());
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAppointmentSelection(String id){
        File file = new File(env+ "/appointmentSelection");
        BufferedWriter output = null;
        try {
            if(!file.exists()){
                file.createNewFile();}

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(id);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemAppointment getAppointmentSelection(){
        File file = new File(env+ "/appointmentSelection");
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }
        System.out.println(text);
        Long id=Long.parseLong(text.toString());
        ItemAppointment res=new ItemAppointment(id,"","","");
        List<ItemAppointment> memberList=readAppointment();
        for(int i=0; i<memberList.size();i++){
            if(memberList.get(i).getId()==id){
                res=memberList.get(i);
            }
        }
        return res;
    }
}
