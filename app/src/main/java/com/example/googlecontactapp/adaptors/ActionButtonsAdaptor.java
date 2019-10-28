package com.example.googlecontactapp.adaptors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googlecontactapp.R;

public class ActionButtonsAdaptor extends RecyclerView.Adapter<ActionButtonsAdaptor.ButtonViewHolder> {

    private String[] buttonNames = {"Call", "Text", "Email", "Map", "Web"};
    private int[] buttonImages = { R.drawable.ic_phone, R.drawable.ic_message, R.drawable.ic_email,
            R.drawable.ic_directions, R.drawable.ic_website};

    private final ActionButtonsAdapterOnClickHandler mClickHandler;

    public interface ActionButtonsAdapterOnClickHandler {
        void onClick(int buttonId);
    }


    public ActionButtonsAdaptor(ActionButtonsAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;

    }

    @NonNull
    @Override
    public ButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForListItem = R.layout.action_btn_list_item;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(layoutIdForListItem, parent, false);

        return new ButtonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return buttonImages.length;
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mActionNameTV;
        private ImageView mActionIV;

        public ButtonViewHolder(@NonNull View itemView) {
            super(itemView);
            mActionIV = itemView.findViewById(R.id.iv_action_ic);
            mActionNameTV = itemView.findViewById(R.id.tv_action_name);

            itemView.setOnClickListener(this);
        }


        void bind(int position) {
            mActionIV.setImageResource(buttonImages[position]);
            mActionNameTV.setText(buttonNames[position]);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickHandler.onClick(position);
        }
    }

}
