package com.company.takenotes;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")

public class Note {

    //Creating the table columns:
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;

    public String description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
    //Getter

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    //Setter:
    public void setId(int id) {
        this.id = id;
    }
}
