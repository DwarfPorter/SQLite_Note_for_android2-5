package ru.geekbrains.notes;

import ru.geekbrains.notes.data.Note;

public interface IRepository {
    Note add(Note note);
    void update(Note note);
    Note get(int position);
    void delete(Note note);
    void deleteAll();
    int getCount();
}
