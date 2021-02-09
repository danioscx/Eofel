package com.eofelx.eofel.adapters;

import com.eofelx.eofel.models.Comments;
import com.eofelx.eofel.models.Posts;

public class Adapter {

     public interface OnPostsClickListener {
          void onClick(Posts posts);
     }

     public interface OnCommentsClickListener {
          void onClick(Comments comments);
     }
}
