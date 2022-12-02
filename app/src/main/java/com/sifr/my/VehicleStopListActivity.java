package com.sifr.my;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.sifr.my.retrofit.Model4TravelStops;

import java.util.ArrayList;
import java.util.List;

import static com.sifr.my.AdapterForSeatsLst.ppModel4VehicleSeatsLst;

public class VehicleStopListActivity extends AppCompatActivity {
    private final List<Model4TravelStops> filteredModel4TravelStops = new ArrayList<>();
    Adapter4travelStops mAdapterForTravelStops;
    public static List<Model4TravelStops> pModel4TravelStops = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_recycler);
        RecyclerView mact_comn_rc_rc = findViewById(R.id.act_comn_rc_rc);
        SearchView mact_comn_rc_sv = findViewById(R.id.act_comn_rc_sv);

        int pp_pod = getIntent().getIntExtra("px_pod", 0);

        if (pModel4TravelStops != null && pModel4TravelStops.size() > 0) {
            mAdapterForTravelStops =
                    new Adapter4travelStops(VehicleStopListActivity.this, pp_pod, pModel4TravelStops);
            mact_comn_rc_rc.setAdapter(mAdapterForTravelStops);
        } else {
            Toast.makeText(VehicleStopListActivity.this, "list is null", Toast.LENGTH_LONG).show();
        }

        mact_comn_rc_sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Model4TravelStops stops : ppModel4VehicleSeatsLst.getTsLst()) {
                    if (stops.getN() != null && stops.getN().contains(newText))
                        filteredModel4TravelStops.add(stops);
                }
                if (filteredModel4TravelStops.size() > 0) {
                    mAdapterForTravelStops.updateList(filteredModel4TravelStops);
                } else {
                    Toast.makeText(VehicleStopListActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });
    }
}