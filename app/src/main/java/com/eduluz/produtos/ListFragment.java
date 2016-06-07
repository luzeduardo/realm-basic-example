package com.eduluz.produtos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.eduluz.produtos.model.Produto;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private Realm realm;
    private Produto produtos;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Configure Realm for the Application
        //Pass to main activity
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RealmResults<Produto> results = realm.where(Produto.class).findAll();
        ListAdapter[] produtosArray = results.toArray(new ListAdapter[results.size()]);
        ArrayAdapter<Produto> prodsArrayAdapter = new ArrayAdapter<Produto>(getActivity(), android.R.layout.simple_list_item_1, results);

        ListView listView = (ListView) getActivity().findViewById(R.id.list_produtos);
        listView.setAdapter(prodsArrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

//        for (Produto prod: results){
//            Log.i("prod", prod.getId() + " - " +  prod.getNome() + " - " + prod.getPreco().toString() + " - " + prod.getBarcode());
//        }
        return v;
    }
}
