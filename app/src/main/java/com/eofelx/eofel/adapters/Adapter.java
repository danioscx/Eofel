package com.eofelx.eofel.adapters;

import com.eofelx.eofel.models.BaseModel;
import com.eofelx.eofel.models.Comments;
import com.eofelx.eofel.models.Posts;

public class Adapter {

     public interface OnPostsClickListener {
          void onClick(Posts posts);
     }

     public interface OnCommentsClickListener {
          void onClick(Comments comments);
     }

     public interface OnClickListener {
          <T extends BaseModel> void onClick(T click);
     }
}
