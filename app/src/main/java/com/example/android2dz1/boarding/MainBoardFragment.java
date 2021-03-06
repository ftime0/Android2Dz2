package com.example.android2dz1.boarding;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.example.android2.R;
import com.example.android2.databinding.FragmentMainBoardBinding;
import com.example.android2dz1.boarding.model.ViewPagerModel;
import com.example.android2dz1.boarding.model.data.DataClient;

import java.util.ArrayList;

public class MainBoardFragment extends Fragment implements OnItemClickListener {
    private FragmentMainBoardBinding binding;
    ViewPagerAdapter adapter;
    ArrayList<ViewPagerModel> list = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBoardBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkOnShow();
        list = DataClient.getData();
        adapter = new ViewPagerAdapter(list,this);
        binding.viewpager.setAdapter(adapter);
        binding.springDotsIndicator.setViewPager2(binding.viewpager);

    }

    private void  checkOnShow (){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        boolean isShow = sharedPreferences.getBoolean("isShow",false);
        if (isShow){
            Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
        }

    }
    @Override
    public void onItemlistener() {
        Navigation.findNavController(requireView()).navigate(R.id.homeFragment);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isShow",true).apply();
    }


    @Override
    public void onStop() {
        super.onStop();
        list.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}











