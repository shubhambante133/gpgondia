package com.example.gpgondia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_TEXT = 0;
    private static final int VIEW_TYPE_IMAGE = 1;

    private Context context;
    private List<ChatMessage> chatMessages;

    public ChatAdapter(Context context, List<ChatMessage> chatMessages) {
        this.context = context;
        this.chatMessages = chatMessages;
    }

    @Override
    public int getItemViewType(int position) {
        // Return the type of message (text or image)
        ChatMessage chatMessage = chatMessages.get(position);
        return chatMessage.getMessage() != null ? VIEW_TYPE_TEXT : VIEW_TYPE_IMAGE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_TEXT) {
            // Inflate the layout for text message
            View view = inflater.inflate(R.layout.chat_item, parent, false);
            return new TextMessageViewHolder(view);
        } else {
            // Inflate the layout for image message
            View view = inflater.inflate(R.layout.chat_item, parent, false);
            return new ImageMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        if (holder instanceof TextMessageViewHolder) {
            // Bind text message
            TextMessageViewHolder textHolder = (TextMessageViewHolder) holder;
            textHolder.textViewMessage.setText(chatMessage.getMessage());
        } else if (holder instanceof ImageMessageViewHolder) {
            // Bind image message
            ImageMessageViewHolder imageHolder = (ImageMessageViewHolder) holder;
            Glide.with(context).load(chatMessage.getImageUrl()).into(imageHolder.imageViewMessage);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    // ViewHolder for text messages
    static class TextMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;

        TextMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textMessage);
        }
    }

    // ViewHolder for image messages
    static class ImageMessageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMessage;

        ImageMessageViewHolder(View itemView) {
            super(itemView);
            imageViewMessage = itemView.findViewById(R.id.imageMessage);
        }
    }
}
