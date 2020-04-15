package com.example.lenovo.medleybranch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addCategory#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addCategory extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addCategory() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addCategory.
     */
    // TODO: Rename and change types and number of parameters
    public static addCategory newInstance(String param1, String param2) {
        addCategory fragment = new addCategory();
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
        final DatabaseReference databaseCategory;
        databaseCategory = FirebaseDatabase.getInstance().getReference("Categories");

        View v = inflater.inflate(R.layout.fragment_add_category, container, false);
        final EditText etCategory = v.findViewById(R.id.etCategoryName);
        Button btnAddCategory = v.findViewById(R.id.btnAddCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = etCategory.getText().toString().trim();
                if(category.isEmpty())
                {
                    Toast.makeText(getContext(), "Give Category Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String id = databaseCategory.push().getKey();
                    CategoryClass cat = new CategoryClass(id,category);
                    databaseCategory.child(id).setValue(cat);
                    Toast.makeText(getContext(), "Category Added Successfully", Toast.LENGTH_SHORT).show();
                    etCategory.setText("");
                }
            }
        });
        return v;
    }
}
