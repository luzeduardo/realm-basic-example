package com.eduluz.produtos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity().getApplicationContext())
                .name("examples.realm")
                .build();

        //Realm.deleteRealm(realConfiguration); //delete the realm

        //Make this real the default
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getInstance(realmConfiguration);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        RealmResults<Produto> results = realm.where(Produto.class).findAll();
        for (Produto prod: results){
            Log.i("prod", prod.getId() + " - " +  prod.getNome() + " - " + prod.getPreco().toString() + " - " + prod.getBarcode());
        }
        return v;
    }
}
