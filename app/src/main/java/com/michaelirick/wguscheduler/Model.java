package com.michaelirick.wguscheduler;

import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;

public class Model {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Intent toIntent(Context context, Class activity) {
        return createIntent(context, activity, null);
   }

   public static String getColumnId(Class activity, String column) {
       try {
           return activity.getDeclaredField(column).toString();
       } catch (NoSuchFieldException e) {
           e.printStackTrace();
       }
       return "";
   }


    public static Intent createIntent(Context context, Class activity, Model m) {
        if(m != null)
            return m.toIntent(context, activity);

        return new Intent(context, activity);
    }

    public String toLongString() {
        return "id: " + id;
    }
}
