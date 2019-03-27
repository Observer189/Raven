package com.example.raven;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.raven.Model.Chat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatsAdapter extends ArrayAdapter<Chat> {
    public ChatsAdapter(@NonNull Context context, List array) {
        super(context, R.layout.chat_item,array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Chat chat=getItem(position);

        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.chat_item,null);
        }

        TextView name=(TextView) convertView.findViewById(R.id.adrName);
        if(chat.getAdresatName()==null)
        {
            name.setText("UNKNOWN");
        }
        else
            name.setText(chat.getAdresatName());

        TextView id=(TextView) convertView.findViewById(R.id.adrId);
        id.setText("id:"+String.valueOf(chat.getAdresatId()));
        if(chat.getLastMessage()!=null) {
            TextView time = (TextView) convertView.findViewById(R.id.mesTime);
            Date date = new Date(chat.getLastMessage().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("(HH:mm:ss)");
            time.setText(dateFormat.format(date));
        }

        TextView message=convertView.findViewById(R.id.lastMessage);
        if(chat.getLastMessage()!=null)
        message.setText(chat.getLastMessage().getText());

        return convertView;
    }
}
