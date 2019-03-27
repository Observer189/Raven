package com.example.raven;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raven.Model.Message;
import com.example.raven.utils.Consts;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, List array) {
        super(context, R.layout.message_item,array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message mes = getItem(position);
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.message_item,null);
        }
        TextView mesText=convertView.findViewById(R.id.mesText);
        mesText.setText(mes.getText());
        if(mes.getAuthorId()== Consts.user.getId())
        {
            mesText.setBackgroundColor(convertView.getResources().getColor(R.color.orange));
        }
        else
        {
            mesText.setBackgroundColor(convertView.getResources().getColor(R.color.gray));
        }

        return convertView;
    }
}
