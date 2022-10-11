package com.example.msg_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    ListView chat = null;
    Context context = null;
    ArrayList<Chat_Element> chats = new ArrayList<>();
    ChatAdapter chad = null;

    interface OnFragmentSendDataListener{
        void onSendData(String data);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        }
        catch(ClassCastException e){
            context.toString();
        }
        this.context = context;
    }

    public ChatFragment() {
        // Required empty public constructor



    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chats.add(new Chat_Element(1, "First Chat"));
        chats.add(new Chat_Element(2,"Second Chat"));
        chat = (ListView) view.findViewById(R.id.chat_list);
        chad = new ChatAdapter(context,R.layout.chat_list_item, chats);
        chat.setAdapter(chad);
        return view;
    }



    class ChatAdapter extends ArrayAdapter<Chat_Element> {
        private LayoutInflater inflater;
        private int layout;
        private ArrayList<Chat_Element> chats;

        ChatAdapter(Context context, int resource, ArrayList<Chat_Element> chats) {
            super(context, resource, chats);
            this.chats = chats;
            this.layout = resource;
            this.inflater = LayoutInflater.from(context);
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(this.layout, parent, false);



            return view;
        }
    }

    class Chat_Element{
        private int ID = 0;
        private String name = null;

        Chat_Element(int id, String Name){
            ID= id;
            name = Name;
        }

        public int getID(){
            return ID;
        }

        public String getName(){
            return name;
        }


    }
}