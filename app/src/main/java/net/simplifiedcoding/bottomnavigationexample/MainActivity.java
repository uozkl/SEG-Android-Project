package net.simplifiedcoding.bottomnavigationexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import java.lang.reflect.Field;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public void testFunc() {
        Toast.makeText(this, "Passed", Toast.LENGTH_LONG).show();
    }

    String currentNumber = "";
    String myNumber = "";
    String hospital= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;

        // loading the default fragment
        loadFragment(new HomeFragment());

        // getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        new JsonHandler().initJsonFile();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
        case R.id.navigation_home:
            fragment = new HomeFragment();
            break;

        case R.id.navigation_queue:
            fragment = new Queue();
            break;

        case R.id.navigation_appointment:
            fragment = new Appointment();
            break;

        case R.id.navigation_prescription:
            fragment = new Prescription();
            break;

        case R.id.navigation_myinfo:
            fragment = new Myinfo();
            break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        // switching fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    public void setQueue(String hosp,String cNum,String mNum){
        hospital = hosp;
        currentNumber = cNum;
        myNumber = mNum;
    }

    public String getMyNumber(){
        return myNumber;
    }

    public String getHospital(){
        return hospital;
    }

    public String getCurrentNumber(){
        return currentNumber;
    }
}

class BottomNavigationViewHelper {
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                // noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                // noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}