package com.company.takenotes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

     private static NoteDatabase instance;

     public  abstract  NoteDAO noteDAO();

     public  static  synchronized  NoteDatabase getInstance(Context context)
     {
         if (instance == null)
         {
             instance = Room.databaseBuilder(context.getApplicationContext(),
                     NoteDatabase.class,"note_database")
                     .fallbackToDestructiveMigration()
                     .addCallback(roomCallback)
                     .build();
         }
         return instance;
     }

     private static RoomDatabase.Callback  roomCallback = new Callback() {
         @Override
         public void onCreate(@NonNull  SupportSQLiteDatabase db) {
             super.onCreate(db);

             new PopulateDbAsyncTask(instance).execute();
         }
     };

     private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>
     {

         private NoteDAO noteDAO;

         private PopulateDbAsyncTask(NoteDatabase database)
         {
             noteDAO = database.noteDAO();
         }

         @Override
         protected Void doInBackground(Void... voids) {

             noteDAO.Insert(new Note("Title 1", "Description 1"));
             noteDAO.Insert(new Note("Title 2", "Description 2"));
             noteDAO.Insert(new Note("Title 3", "Description 3"));

             return null;
         }
     }

}
