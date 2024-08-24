package com.example.nutrition.Model;

import android.media.MediaRoute2Info;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MRModel {
    protected String myth;
    protected String reality;

    public MRModel(String myth, String reality){
        this.myth = myth;
        this.reality = reality;
    }

    private MRModel(){
        this.myth = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
        this.reality = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum";
    }

    public static MRModel createAnEmptyMRModel(){
        return new MRModel();
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
