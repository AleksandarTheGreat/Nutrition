package com.example.nutrition.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MRModel {
    protected String myth;
    protected String reality;
    public MRModel(String myth, String reality){
        this.myth = myth;
        this.reality = reality;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (getClass() == null || obj.getClass() == null)
            return false;
        else if (getClass() != obj.getClass())
            return false;
        return myth.equals(((MRModel) obj).myth)
                && reality.equals(((MRModel) obj).reality);
    }

    @NonNull
    @Override
    public String toString() {
        return myth + " " + reality;
    }

    public String getMyth() {
        return myth;
    }

    public void setMyth(String myth) {
        this.myth = myth;
    }

    public String getReality() {
        return reality;
    }

    public void setReality(String reality) {
        this.reality = reality;
    }
}
