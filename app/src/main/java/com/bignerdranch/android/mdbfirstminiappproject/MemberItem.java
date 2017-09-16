package com.bignerdranch.android.mdbfirstminiappproject;

/**
 * Created by pauls on 9/15/2017.
 */

public class MemberItem {
    //the name of the MDB member
    //the id referencing the member's picture
    String name;
    int image;

    //creating a constructor containing the member's name and member's picture
    public MemberItem(String name, int image)
    {
        this.name = name;
        this.image = image;
    }

    //retrieval method
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }


}
