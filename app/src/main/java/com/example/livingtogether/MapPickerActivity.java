package com.example.livingtogether;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.livingtogether.databinding.ActivityMapPickerBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MapPickerActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng selectedLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMapPickerBinding binding = ActivityMapPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        binding.btnBackMap.setOnClickListener(v -> finish());

        binding.btnConfirmLocation.setOnClickListener(v -> {
            if (mMap != null) {
                selectedLocation = mMap.getCameraPosition().target;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("latitude", selectedLocation.latitude);
                resultIntent.putExtra("longitude", selectedLocation.longitude);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Kathmandu coordinates
        LatLng kathmandu = new LatLng(27.7172, 85.3240);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kathmandu, 13f));

        mMap.setOnCameraIdleListener(() -> {
            selectedLocation = mMap.getCameraPosition().target;
        });
    }
}