package ru.geekbrains.notes.dataRoom;

import android.database.Cursor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import ru.geekbrains.notes.data.Note;

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertNote(Note note);

    // Метод для замены данных
    @Update
    void update(Note note);

    // Удалим данные
    @Delete
    void delete(Note note);

    //Получить количество записей в таблице
    @Query("SELECT COUNT() FROM NOTE")
    int getCount();

    @Query("DELETE FROM NOTE")
    void deleteAll();

    @Query("SELECT ID, DESCRIPTION, TITLE FROM NOTE")
    Cursor getNotes();

    @Query("SELECT ID, DESCRIPTION, TITLE FROM NOTE WHERE ID=:id")
    Note getNoteById(long id);

    @Query("SELECT ID, DESCRIPTION, TITLE FROM NOTE LIMIT 1 OFFSET :position")
    Note getNote(int position);
}
