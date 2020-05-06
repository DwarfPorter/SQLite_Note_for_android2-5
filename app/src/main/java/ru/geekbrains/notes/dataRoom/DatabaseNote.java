package ru.geekbrains.notes.dataRoom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.geekbrains.notes.data.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class DatabaseNote extends RoomDatabase {
    public abstract NoteDao getNoteDao();
}
