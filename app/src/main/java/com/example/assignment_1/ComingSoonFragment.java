package com.example.assignment_1;

import android.graphics.Movie;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComingSoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComingSoonFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<MovieDetails> list;
    MovieAdapter adapter;
    TextView tvMsg;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComingSoonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComingSoonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComingSoonFragment newInstance(String param1, String param2) {
        ComingSoonFragment fragment = new ComingSoonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coming_soon, container, false);

        // Header text
        tvMsg = view.findViewById(R.id.tvMsg);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast), Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvMsg.setText(Html.fromHtml(getString(R.string.cinefast)));
        }

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerComingSoon);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();


// Add movies with image/logo
        list.add(new MovieDetails("Aag Lagay Basti Mein",
                "https://www.youtube.com/watch?v=KozPWehBjvs",
                R.drawable.agg_lagay,true));

        list.add(new MovieDetails("Young sheldon",
                "https://www.youtube.com/watch?v=kVrqfYjkTdQ",
                R.drawable.titanic,true));

        list.add(new MovieDetails("Megan",
                "https://www.youtube.com/watch?v=zSWdZVtXT7E",
                R.drawable.intersteller,true));
        adapter = new MovieAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        return view;
    }


}