package ru.geekbrains.notes.ui;

import ru.geekbrains.notes.NoteLogic;

// интерфейс для передачи класса логики в активити
// Часть работы будет из фрагмента, часть из активити
public interface GetableNoteLogic {
    void putNoteLogic(NoteLogic noteLogic);
}
