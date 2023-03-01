package com.example.muplayer.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.muplayer.R;
import com.example.muplayer.adapter.AudiosAdapter;
import com.example.muplayer.utils.AudioDataUtil;

public class HomeFragment extends Fragment implements AudiosAdapter.OnClickListener{

    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private AudiosAdapter adapter;
    private HomeFragmentListener listener;
    public HomeFragment(HomeFragmentListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getActivity().findViewById(R.id.rv_audios);
        adapter = new AudiosAdapter(AudioDataUtil.getInstance().getAudioItemList(), this::onClick);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClick(int pos) {
        Log.d(TAG, "onClick: "+pos);
        listener.openMusicPlayerFragment(pos);
    }

    public interface HomeFragmentListener {
        public void openMusicPlayerFragment(int pos);
    }
}
