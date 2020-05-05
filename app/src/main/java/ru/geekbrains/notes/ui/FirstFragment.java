package ru.geekbrains.notes.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.geekbrains.notes.NoteLogic;
import ru.geekbrains.notes.R;

public class FirstFragment extends Fragment {

    private NoteLogic noteLogic;
    private NoteAdapter adapter;                // Адаптер для RecyclerView

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View viewFragment = inflater.inflate(R.layout.fragment_first, container, false);

        initRecyclerView(viewFragment);

        return viewFragment;
    }

    private void initRecyclerView(View viewFragment) {
        noteLogic = new NoteLogic(getContext());
        RecyclerView recyclerView = viewFragment.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoteAdapter(noteLogic.getNoteDataReader());
        noteLogic.setAdaper(adapter);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GetableNoteLogic activity = (GetableNoteLogic) getActivity();
        activity.putNoteLogic(noteLogic);
    }
}
