package com.eofelx.eofel.adapters;

import com.eofelx.eofel.models.Comments;
import com.eofelx.eofel.models.Posts;
import com.google.android.material.textfield.TextInputEditText;

public class Adapter {

     public interface OnPostsClickListener {
          void onClick(Posts posts);
     }

     public interface OnCommentsClickListener {
          void onClick(Comments comments);
     }

     public interface OnEndIconClickListener {
          void onClick(Comments comments, TextInputEditText editText);
     }
}
