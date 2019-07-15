package net.simplifiedcoding.bottomnavigationexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<HospitalNames> hospitalNamesList = null;
    private ArrayList<HospitalNames> arraylist;

    public ListViewAdapter(Context context, List<HospitalNames> hospitalNamesList) {
        mContext = context;
        this.hospitalNamesList = hospitalNamesList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<HospitalNames>();
        this.arraylist.addAll(hospitalNamesList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return hospitalNamesList.size();
    }

    @Override
    public HospitalNames getItem(int position) {
        return hospitalNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(hospitalNamesList.get(position).getHospitalName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        hospitalNamesList.clear();
        if (charText.length() == 0) {
            hospitalNamesList.addAll(arraylist);
        } else {
            for (HospitalNames wp : arraylist) {
                if (wp.getHospitalName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    hospitalNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
