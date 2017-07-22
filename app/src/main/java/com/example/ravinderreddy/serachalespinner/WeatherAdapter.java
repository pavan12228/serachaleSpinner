package com.example.ravinderreddy.serachalespinner;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WeatherAdapter extends ArrayAdapter<Person> {

    Context context;
    int layoutResourceId;
    List<Person> personArrayList;
    LayoutInflater layoutInflater;
    Holder holder;

    public WeatherAdapter(Context context, int resourceId, List<Person> personArrayList) {
        super(context, resourceId, personArrayList);
        this.context = context;
        this.personArrayList=personArrayList;
        holder = new Holder();
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view =layoutInflater.inflate(R.layout.inflator_row_item,null);
        }
         holder.name = (TextView) view.findViewById(R.id.name);
        Person person = personArrayList.get(position);
        holder.name.setText(person.getName().toString());
        return view;
    }


    private class Holder {
        TextView  name;
    }
}