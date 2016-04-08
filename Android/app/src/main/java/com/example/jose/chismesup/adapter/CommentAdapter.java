package com.example.jose.chismesup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jose.chismesup.Comment;
import com.example.jose.chismesup.R;

import java.util.List;

/**
 * Created by Jose on 4/7/2016.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {

    private List<Comment> commentList;
    private Context context;

    public CommentAdapter(List<Comment> commentList, Context context) {
        super(context, R.layout.list_element, commentList);
        this.commentList = commentList;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_element, parent, false);
        }

        TextView tvUser = (TextView) view.findViewById(R.id.tvUser);
        TextView tvComment = (TextView) view.findViewById(R.id.tvComment);
        Comment comment = commentList.get(position);

        tvUser.setText(comment.getUser());
        tvComment.setText(comment.getComment());

        return view;
    }

}
