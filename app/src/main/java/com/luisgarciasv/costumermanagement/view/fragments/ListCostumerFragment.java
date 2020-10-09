package com.luisgarciasv.costumermanagement.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luisgarciasv.costumermanagement.R;
import com.luisgarciasv.costumermanagement.model.Costumer;
import com.luisgarciasv.costumermanagement.view.CostumerListener;
import com.luisgarciasv.costumermanagement.viewmodel.CostumerViewModel;

import java.util.List;

public class ListCostumerFragment extends Fragment implements CostumerListener {

    private RecyclerView rvListCostumer;
    private CostumerViewModel viewModel;
    private List<Costumer> costumerList;
    private CostumerAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        viewModel = new ViewModelProvider(requireActivity()).get(CostumerViewModel.class);
        return inflater.inflate(R.layout.fragment_list_costumer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataRv(view);

        view.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ListCostumerFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void setDataRv(View view) {
        adapter = new CostumerAdapter(costumerList, this);
        rvListCostumer = view.findViewById(R.id.rv_list_costumer);
        rvListCostumer.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvListCostumer.setLayoutManager(layoutManager);
        rvListCostumer.setAdapter(adapter);
        loadCostumers();
    }

    private void loadCostumers(){
        viewModel.getCustomers().observe(getViewLifecycleOwner(), new Observer<List<Costumer>>() {
            @Override
            public void onChanged(List<Costumer> costumers) {
                costumerList = costumers;
                adapter.setData(costumerList);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCostumers();
    }

    @Override
    public void getDuiClicked(String dui) {

        Bundle bundle = new Bundle();
        bundle.putString("Dui",dui);

        NavHostFragment.findNavController(ListCostumerFragment.this)
                .navigate(R.id.action_ListCostumerFragment_to_DetailCostumerFragment,bundle);
    }
}