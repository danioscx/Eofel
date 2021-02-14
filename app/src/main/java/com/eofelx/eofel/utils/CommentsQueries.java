package com.eofelx.eofel.utils;

import com.eofelx.eofel.models.Comments;

import java.util.List;

public class CommentsQueries {

    public interface OnCommentRequestListener {
        void onRequest(List<Comments> comments);
    }

    public interface OnCommentCallback {
        void onResult(List<Comments> comments);
    }


}
