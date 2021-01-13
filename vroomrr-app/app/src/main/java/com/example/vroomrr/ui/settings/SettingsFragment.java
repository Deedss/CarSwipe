package com.example.vroomrr.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.Cryptography;
import com.example.vroomrr.MultipleSelectionSpinner;
import com.example.vroomrr.R;
import com.example.vroomrr.SearchFilter;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class SettingsFragment extends Fragment implements ServerCallback {
    private View root;
    private NumberPicker np_build_year_min;
    private NumberPicker np_build_year_max;
    private NumberPicker np_horsepower_min;
    private MultipleSelectionSpinner sp_fuel_types;
    private MultipleSelectionSpinner sp_brands;
    private MultipleSelectionSpinner sp_colors;

    private List<String> brands = new ArrayList<>();
    private List<String> fuel_types = new ArrayList<>();
    private List<String> colors = new ArrayList<>();

    private SearchFilter filter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.settings_menu, menu);

        menu.findItem(R.id.action_save).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                updateFilter();
                return true;
            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);

        setupViews();
        getFilter();

        return root;
    }

    /**
     *  Get the current filter for the user
     */
    private void getFilter() {
        ServerConnection.getFilter(new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                filter = new Gson().fromJson(object, SearchFilter.class);
                fillOptions();
            }
        }, this.getActivity());
    }

    /**
     * Fill all the selected options depending on the filter from the server
     */
    private void fillOptions() {
        np_build_year_min.setValue(filter.getBuild_year_min());
        np_build_year_max.setValue(filter.getBuild_year_max());
        np_horsepower_min.setValue(filter.getHorsepower_min());
        sp_brands.setSelection(filter.getBrands().split(","));
        sp_fuel_types.setSelection(filter.getFuel_types().split(","));
        sp_colors.setSelection(filter.getFuel_types().split(","));
    }

    /**
     * Update the filter to the server.
     */
    private void updateFilter() {
        filter.setUser_id(Cryptography.getFromSharedPreferences(this.getContext(), String.valueOf(R.string.UserId)));
        filter.setBuild_year_min(np_build_year_min.getValue());
        filter.setBuild_year_max(np_build_year_max.getValue());
        filter.setHorsepower_min(np_horsepower_min.getValue());
        filter.setFuel_types(sp_fuel_types.getSelectedItemsAsString().replace(" ", ""));
        filter.setBrands(sp_brands.getSelectedItemsAsString().replace(" ", ""));
        filter.setColors(sp_colors.getSelectedItemsAsString().replace(" ", ""));

        ServerConnection.updateFilter(filter, new ServerCallback() {
            @Override
            public void completionHandler(String object, String url) {
                Toast.makeText(getActivity(), "Updated your search filter", Toast.LENGTH_LONG).show();
            }
        }, this.getActivity());
    }

    /**
     * Setup all the views for this fragment.
     */
    private void setupViews(){
        // NumberPickers
        np_build_year_min = root.findViewById(R.id.build_year_min);
        np_build_year_min.setMinValue(1900);
        np_build_year_min.setValue(1900);
        np_build_year_min.setMaxValue(9999);
        np_build_year_min.setEnabled(true);

        np_build_year_max = root.findViewById(R.id.build_year_max);
        np_build_year_max.setMinValue(1900);
        np_build_year_max.setValue(1900);
        np_build_year_max.setMaxValue(9999);
        np_build_year_max.setEnabled(true);

        np_horsepower_min = root.findViewById(R.id.horsepower_min);
        np_horsepower_min.setMinValue(0);
        np_horsepower_min.setValue(0);
        np_horsepower_min.setMaxValue(9999);
        np_horsepower_min.setEnabled(true);


        // Spinners
        sp_fuel_types = root.findViewById(R.id.fuel_types);
        sp_brands = root.findViewById(R.id.brands);
        sp_colors = root.findViewById(R.id.colors);
        fillArrayLists();
        sp_fuel_types.setItems(fuel_types);
        sp_brands.setItems(brands);
        sp_colors.setItems(colors);
    }

    /**
     * Fill the arrayLists for SelectionSpinner
     */
    private void fillArrayLists(){
        brands.add("Audi");
        brands.add("BMW");
        brands.add("Mercedes-Benz");
        brands.add("Volkswagen");
        brands.add("Ford");
        brands.add("Opel");
        brands.add("Renault");
        brands.add("Peugeot");
        brands.add("Citroen");
        brands.add("Volvo");

        fuel_types.add("lpg");
        fuel_types.add("diesel");
        fuel_types.add("benzine");
        fuel_types.add("elektriciteit");

        colors.add("grijs");
        colors.add("zwart");
        colors.add("rood");
        colors.add("blauw");
        colors.add("groen");
        colors.add("geel");
        colors.add("paars");
        colors.add("wit");
    }

    @Override
    public void completionHandler(String object, String url) {

    }
}