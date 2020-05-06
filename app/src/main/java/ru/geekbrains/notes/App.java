package ru.geekbrains.notes;

import android.app.Application;

import androidx.room.Room;

import ru.geekbrains.notes.dataRoom.DatabaseNote;
import ru.geekbrains.notes.dataRoom.NoteDao;

// паттерн синглтон, наследуем класс Application
// создаем базу данных в методе onCreate
public class App extends Application {

    private static App instance;

    // база данных
    private DatabaseNote db;

    // Так получаем объект приложения
    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Это для синглтона, сохраняем объект приложения
        instance = this;

        // строим базу
        db = Room.databaseBuilder(
                getApplicationContext(),
                DatabaseNote.class,
                "notes_database")
                .allowMainThreadQueries() //Только для примеров и тестирования.
                .build();
    }

    // Получаем EducationDao для составления запросов
    public NoteDao getNoteDao() {
        return db.getNoteDao();
    }
}
