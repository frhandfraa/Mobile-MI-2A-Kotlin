package farhan.defra.kotlin.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import farhan.defra.kotlin.R
import farhan.defra.kotlin.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val zoomMap = 15f

        // Add a marker in Sydney and move the camera
        val pnp = LatLng(-0.9143104384746342, 100.46612954204886)
        mMap.addMarker(MarkerOptions().position(pnp).title("Politeknik Negeri Padang"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pnp, zoomMap))

        // tambahkan data Map
        val listMap = listOf(
            MapModel(-0.954188150677427, 100.3592158018559, "The Axana Hotel"),
            MapModel(-0.9158189110864111, 100.3598114707154, "Whiz Prime Hotel"),
            MapModel(-0.921922553545177, 100.35192525845096, "Pangeran Beach Hotel"),
            MapModel(-0.8969260271439145, 100.35068998404553, "Universitas Negeri Padang")
        )
        listMap.forEach {
            mMap.addMarker(MarkerOptions()
                .position(LatLng(it.Lat, it.Lng))
                .title(it.Title)
            )
        }
    }
}