package com.app.email.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.email.R;
import com.app.email.model.Email;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailHolder> {

    private final Context mContext;
    private List<Email> mData;

    public EmailAdapter(Context mContext, List<Email> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public EmailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_email, parent, false);
        return new EmailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmailHolder holder, int position) {
        Email email = mData.get(position);

        //set data
        holder.to.setText(email.getTo());
        holder.subject.setText(email.getSubject());
        holder.content.setText(email.getContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void updateView(Email email) {
        mData.add(email);
        notifyDataSetChanged();
    }

    static class EmailHolder extends RecyclerView.ViewHolder {

        final TextView to;
        final TextView subject;
        final TextView content;

        public EmailHolder(@NonNull View itemView) {
            super(itemView);

            to = itemView.findViewById(R.id.textView_list_item_email_to);
            subject = itemView.findViewById(R.id.textView_list_item_email_subject);
            content = itemView.findViewById(R.id.textView_list_item_email_content);
        }
    }
}
