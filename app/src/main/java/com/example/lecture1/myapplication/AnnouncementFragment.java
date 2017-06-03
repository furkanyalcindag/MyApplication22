package com.example.lecture1.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;



public class AnnouncementFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    public ArrayList<String> arrayList;
    public ArrayList<String> links;
    ListView listView;
    public ArrayAdapter<String> adapter;

    public AnnouncementFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_announcement, container, false);
        listView=(ListView)view.findViewById(R.id.listView);

        return view;
    }

    private class Description extends AsyncTask<Void, Void, Void> {
        String desc;


        @Override
        protected Void doInBackground(Void... params)   {
            arrayList = new ArrayList<String>();
            try {
                arrayList= new ArrayList<String>();
                links= new ArrayList<String>();
                Document document = Jsoup.connect("http://www.ybu.edu.tr/muhendislik/bilgisayar/").get();
                Element masthead = document.select("div.caContent").first();
                Iterator<Element> ite = masthead.select("div.cncItem").iterator();
                //ite.next();

                while(ite.hasNext()){
                    Element div =ite.next();
                    arrayList.add(div.text());
                    System.out.println("Value 1: " + div.select("a").attr("href"));
                    links.add(div.select("a").attr("href"));



                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return null;

            }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected void onPostExecute(Void result) {
            ArrayAdapter a=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,arrayList);
            listView.setAdapter(a);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {


                    String[] a = new String[10000];
                    for(int i=0;i<links.size();i++){
                        a[i]="http://www.ybu.edu.tr/muhendislik/bilgisayar/"+links.get(i).toString();
                    }

                    Uri uri = Uri.parse(a[position]);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);




            }
        });

        // foodList.setText(a);
        //mProgressDialog.dismiss();
    }
}}