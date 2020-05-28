package mnstate.example.projectthree;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class covidmainmaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST_CODE =101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covidmainmaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        UiSettings mapSettings;
        mapSettings=mMap.getUiSettings();
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setZoomGesturesEnabled(true);
        mapSettings.setScrollGesturesEnabled(true);
        mapSettings.setTiltGesturesEnabled(true);
        mapSettings.setRotateGesturesEnabled(true);

        // Add a marker in Sydney and move the camera
        //LatLng kumamoto = new LatLng(33, 131);
        //mMap.addMarker(new MarkerOptions().position(kumamoto).title("Marker in Kumamoto Japan"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(kumamoto));

        LatLng kumamoto = new LatLng(44, -93);
        mMap.addMarker(new MarkerOptions().position(kumamoto).title("United States of America (Red) "));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kumamoto));




        Marker myHome=mMap.addMarker(new MarkerOptions().position(new LatLng(27,85)).title("Country").snippet("Peoples Republic of Nepal(Green-zone)")
        );


        Marker osaka=mMap.addMarker(new MarkerOptions().position(new LatLng(34,135)).title("Place ").snippet("Japan (Blue-zone)")
        );





        Marker spain=mMap.addMarker(new MarkerOptions().position(new LatLng(40,4)).title("COVID-19 Impact").snippet("Spain (Red-zone)")
        );
        Marker italy =mMap.addMarker(new MarkerOptions().position(new LatLng(41,12)).title("COVID-19 Impact ").snippet("Italy(Red-zone)")
        );
        Marker uk=mMap.addMarker(new MarkerOptions().position(new LatLng(55,3)).title("COVID-19 Impact ").snippet("United Kingdom(Red-zone)")
        );
        Marker ger=mMap.addMarker(new MarkerOptions().position(new LatLng(51,10)).title("COVID-19 Impact ").snippet("Germany(Red-zone)")
        );
        Marker france=mMap.addMarker(new MarkerOptions().position(new LatLng(46,2)).title("COVID-19 Impact ").snippet("France(Red-zone)")
        );
        Marker turkey=mMap.addMarker(new MarkerOptions().position(new LatLng(38,35)).title("COVID-19 Impact").snippet("Turkey(Red-zone)")
        );


        Marker India=mMap.addMarker(new MarkerOptions().position(new LatLng(20,78)).title("COVID-19 Impact").snippet("India (Blue-zone)")
        );
        Marker Peru =mMap.addMarker(new MarkerOptions().position(new LatLng(9,75)).title("COVID-19 Impact ").snippet("Italy (Blue-zone)")
        );
        Marker swit=mMap.addMarker(new MarkerOptions().position(new LatLng(46,8)).title("COVID-19 Impact ").snippet("United Kingdom (Blue-zone)")
        );
        Marker portugal=mMap.addMarker(new MarkerOptions().position(new LatLng(39,8)).title("COVID-19 Impact ").snippet("Germany (Blue-zone)")
        );
        Marker Ecudor=mMap.addMarker(new MarkerOptions().position(new LatLng(2,78)).title("COVID-19 Impact ").snippet("France (Blue-zone)")
        );
        Marker chad=mMap.addMarker(new MarkerOptions().position(new LatLng(38,35)).title("COVID-19 Impact").snippet("Chad (Green-zone)")

        );
        Marker zim=mMap.addMarker(new MarkerOptions().position(new LatLng(19,29)).title("COVID-19 Impact").snippet("Chad (Green-zone)")

        );

        //try

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://coronavirus.jhu.edu/map.html"));
                startActivity(browserIntent);
                return true;

            }

        });












        CameraPosition cameraPosition=new CameraPosition.Builder().target(new LatLng(44,-93)).zoom(10).bearing(70).tilt(25).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.addCircle(
                new CircleOptions().center(new LatLng(44,-93)).radius(500.0).strokeWidth(10f).strokeColor(android.R.color.holo_red_light).fillColor(Color.argb(70,236,108,108))
        );

        //polygon

        Polygon polygon1 = mMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(39, 116),
                        new LatLng(31, 121),
                        new LatLng(26, 117),
                        new LatLng(30, 112)).strokeColor(Color.RED).fillColor(Color.argb(70,236,108,108)));
// Store a data object with the polygon, used here to indicate an arbitrary type.
        polygon1.setTag("Place of originated");





        if(mMap!=null){
            int permission= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if(permission== PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }
            else{
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION_REQUEST_CODE);
            }
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
    }

    protected void requestPermission(String permissionType,int requestCode){
        ActivityCompat.requestPermissions(this,new String[]{permissionType},requestCode);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode){
            case LOCATION_REQUEST_CODE:
                if(grantResults.length==0|| grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"Cannot show Location - permission required",Toast.LENGTH_LONG).show();

                }

                else{
                    SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
        }
    }
}
