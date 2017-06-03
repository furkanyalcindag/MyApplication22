package com.example.lecture1.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class FoodFragment extends Fragment {
    public TextView textView;

    Document doc = null;
    public ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;

    public FoodFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_food, container, false);

        textView=(TextView)view.findViewById(R.id.textView);
        new Description().execute();
        return view;

    }

    // Description AsyncTask
    private class Description extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void result) {
            String list = "";
            for (int i = 0; i < arrayList.size(); i++) {
                list = list + "\n" + arrayList.get(i).toString();

            }
            textView.setText(list);
        }

        @Override
        protected Void doInBackground(Void... params) {
            arrayList = new ArrayList<String>();
            try {

                URL url = new URL("http://ybu.edu.tr/sks/");
                Document doc = Jsoup.parse(url, 2000);
                Element element = doc.select("table").first();
                Iterator<Element> elementIterator = element.select("td").iterator();
                elementIterator.next();
                while (elementIterator.hasNext()) {
                    arrayList.add(elementIterator.next().text());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}







