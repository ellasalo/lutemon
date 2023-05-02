package com.olio.lutemon.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.olio.lutemon.LutemonAddingActivity;
import com.olio.lutemon.LutemonClasses.Lutemon;
import com.olio.lutemon.LutemonClasses.Storage;
import com.olio.lutemon.LutemonListAdapter;
import com.olio.lutemon.MainActivity;
import com.olio.lutemon.R;
import com.olio.lutemon.TabGameMode;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rcvLutemons;
    private Button btnSaveAndRest;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Lutemon> lutemons;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcvLutemons = view.findViewById(R.id.rcvItems);
        btnSaveAndRest = view.findViewById(R.id.btnHealSave);
        rcvLutemons.setLayoutManager(new LinearLayoutManager(getContext()));
        LutemonListAdapter lutemonListAdapter = new LutemonListAdapter(getContext(), Storage.getInstance().getLutemons());
        rcvLutemons.setAdapter(lutemonListAdapter);
        lutemonListAdapter.notifyDataSetChanged();

        btnSaveAndRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Lutemon> lutemons = Storage.getInstance().getLutemons();
                for(Lutemon lutemon : lutemons) {
                    lutemon.restLutemon();
                }
                Storage.getInstance().saveLutemons(getContext());
                lutemonListAdapter.notifyDataSetChanged();
            }
        });
    }


    }
