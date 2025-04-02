package com.example.gpgondia;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<ChatMessage> chatMessages;
    private Context context;

    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        if (chatMessage.hasImage()) {
            holder.textMessage.setVisibility(View.GONE);
            holder.imageMessage.setVisibility(View.VISIBLE);
            Glide.with(context).load(chatMessage.getImageUri()).into(holder.imageMessage);
        } else {
            holder.textMessage.setVisibility(View.VISIBLE);
            holder.imageMessage.setVisibility(View.GONE);
            holder.textMessage.setText(chatMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textMessage;
        ImageView imageMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textMessage = itemView.findViewById(R.id.textMessage);
            imageMessage = itemView.findViewById(R.id.imageMessage);
        }
    }
}
