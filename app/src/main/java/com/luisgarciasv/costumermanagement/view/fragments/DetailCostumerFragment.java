package com.luisgarciasv.costumermanagement.view.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.luisgarciasv.costumermanagement.R;
import com.luisgarciasv.costumermanagement.model.Costumer;
import com.luisgarciasv.costumermanagement.viewmodel.CostumerViewModel;


public class DetailCostumerFragment extends Fragment {

    private CostumerViewModel viewModel;
    private ImageView btnClose, btnDelete;
    private TextInputLayout inputName, inputLastName, inputDui, inputNit, inputAddress, inputPhone, inputEmail;
    private Button btnSave;

    public DetailCostumerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(requireActivity()).get(CostumerViewModel.class);
        return inflater.inflate(R.layout.fragment_detail_costumer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);

        getCostumerDetail();

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(
                        DetailCostumerFragment.this).popBackStack();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDelete();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCostumer();
            }
        });

    }

    private void validateDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_message_delete)
                .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        deleteCostumer();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();
    }

    private void deleteCostumer() {
        String dui = getArguments().getString("Dui");
        viewModel.deleteCostumer(dui);
        NavHostFragment.findNavController(DetailCostumerFragment.this).popBackStack();
    }

    private void getCostumerDetail() {
        String dui = getArguments().getString("Dui");
        viewModel.getCostumer(dui).observe(getViewLifecycleOwner(), new Observer<Costumer>() {
            @Override
            public void onChanged(Costumer costumer) {
                inputName.getEditText().setText(costumer.getName());
                inputLastName.getEditText().setText(costumer.getLastName());
                inputDui.getEditText().setText(costumer.getDui());
                inputNit.getEditText().setText(costumer.getNit());
                inputAddress.getEditText().setText(costumer.getAddress());
                inputPhone.getEditText().setText(costumer.getPhone());
                inputEmail.getEditText().setText(costumer.getEmail());
            }
        });
    }

    private void updateCostumer() {
        if (!validateName() |
                !validateLastName() |
                !validateDui() |
                !validateNit() |
                !validateAddress() |
                !validatePhone() |
                !validateEmail()){

        }else {

            String name = inputName.getEditText().getText().toString();
            String lastName = inputLastName.getEditText().getText().toString();
            String dui = inputDui.getEditText().getText().toString();
            String nit = inputNit.getEditText().getText().toString();
            String address = inputAddress.getEditText().getText().toString();
            String phone = inputPhone.getEditText().getText().toString();
            String email = inputEmail.getEditText().getText().toString();

            Costumer costumer = new Costumer(name,lastName,dui,nit,address,phone,email);

            viewModel.updateCostumer(costumer);
            Toast.makeText(getActivity(), R.string.toas_update_costumer, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(DetailCostumerFragment.this).popBackStack();
        }
    }

    private boolean validateName(){
        String item = inputName.getEditText().getText().toString();

        if (item.isEmpty()){
            inputName.setError(getString(R.string.obligatory_field));
            return false;
        } else {
            inputName.setError(null);
            inputName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateLastName(){
        String item = inputLastName.getEditText().getText().toString();

        if (item.isEmpty()){
            inputLastName.setError(getString(R.string.obligatory_field));
            return false;
        } else {
            inputLastName.setError(null);
            inputLastName.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDui(){
        String item = inputDui.getEditText().getText().toString();

        if (item.isEmpty()){
            inputDui.setError(getString(R.string.obligatory_field));
            return false;
        } else if (item.length() != 9){
            inputDui.setError(getString(R.string.error_dui));
            return false;
        } else {
            inputDui.setError(null);
            inputDui.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateNit(){
        String item = inputNit.getEditText().getText().toString();

        if (item.isEmpty()){
            inputNit.setError(getString(R.string.obligatory_field));
            return false;
        } else if (item.length() != 14){
            inputNit.setError(getString(R.string.error_nit));
            return false;
        } else {
            inputNit.setError(null);
            inputNit.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress(){
        String item = inputAddress.getEditText().getText().toString();

        if (item.isEmpty()){
            inputAddress.setError(getString(R.string.obligatory_field));
            return false;
        } else {
            inputAddress.setError(null);
            inputAddress.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone(){
        String item = inputPhone.getEditText().getText().toString();

        if (item.isEmpty()){
            inputPhone.setError(getString(R.string.obligatory_field));
            return false;
        } else if (item.length() != 8){
            inputPhone.setError(getString(R.string.error_phone));
            return false;
        } else {
            inputPhone.setError(null);
            inputPhone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail(){
        String item = inputEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";

        if (item.isEmpty()){
            inputEmail.setError(getString(R.string.obligatory_field));
            return false;
        } else if (!item.matches(emailPattern)){
            inputEmail.setError(getString(R.string.error_email));
            return false;
        } else {
            inputEmail.setError(null);
            inputEmail.setErrorEnabled(false);
        }
        return true;
    }

    private void initViews(View view) {
        btnClose = view.findViewById(R.id.btn_close);
        btnSave = view.findViewById(R.id.btn_save);
        btnDelete = view.findViewById(R.id.btn_delete);
        inputName = view.findViewById(R.id.input_name);
        inputLastName = view.findViewById(R.id.input_last_name);
        inputDui = view.findViewById(R.id.input_dui);
        inputNit = view.findViewById(R.id.input_nit);
        inputAddress = view.findViewById(R.id.input_address);
        inputPhone = view.findViewById(R.id.input_phone);
        inputEmail = view.findViewById(R.id.input_email);
    }


}