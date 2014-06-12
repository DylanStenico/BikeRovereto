package smartcampus.activity;

import java.util.ArrayList;

import smartcampus.model.Station;
import smartcampus.util.CustomInfoWindow;
import smartcampus.util.MarkerOverlay;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import com.example.openstreetmap.R;

import eu.trentorise.smartcampus.osm.android.bonuspack.overlays.ExtendedOverlayItem;
import eu.trentorise.smartcampus.osm.android.views.MapController;
import eu.trentorise.smartcampus.osm.android.views.MapView;
import eu.trentorise.smartcampus.osm.android.views.overlay.MyLocationOverlay;

public class OsmMap extends Activity
{
	// the view where the map is showed
	private MapView mapView;

	// the tools to control the map
	private MapController mapController;

	MyLocationOverlay myLoc;

	// the stations to show in the map
	ArrayList<Station> stations;

	// marker for the stations
	MarkerOverlay<ExtendedOverlayItem> stationsMarkersOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_osm_map);

		// get the mapView and the controller
		mapView = (MapView) findViewById(R.id.map_view);
		mapController = mapView.getController();

		// mapView.setBuiltInZoomControls(true);
		mapView.setMultiTouchControls(true);

		// to keep the display on
		mapView.setKeepScreenOn(true);

		// get the station from the parcels
		stations = getIntent().getExtras().getParcelableArrayList("stations");

		// stuff for my Location
		myLoc = new MyLocationOverlay(getApplicationContext(), mapView);
		myLoc.enableMyLocation();
		myLoc.enableCompass();
		mapView.getOverlays().add(myLoc);

		// add the markers on the mapView
		addMarkers();
//		MapOverlay mapOverlay = new MapOverlay(this);
//		mapView.getOverlays().add(mapOverlay);

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasFocus);
		mapView.zoomToBoundingBox(Station.getBoundingBox(stations));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void addMarkers()
	{
		// markers at:
		// http://openclipart.org/detail/184847/map-marker-vector-by-rfvectors.com-184847
		Resources res = getResources();

		ArrayList<ExtendedOverlayItem> markers = new ArrayList<ExtendedOverlayItem>();

		for (int i = 0; i < stations.size(); i++)
		{
			markers.add(new ExtendedOverlayItem(stations.get(i)
					.getDescription(), "station", stations.get(i).getPosition()));

			Drawable markerImage = null;
			if (stations.get(i).getUsedPercentage() < 0.5)
			{
				markerImage = res.getDrawable(R.drawable.marker_green);
			}
			else if (stations.get(i).getUsedPercentage() < 1.0)
			{
				markerImage = res.getDrawable(R.drawable.marker_yellow);
			}
			else
			{
				markerImage = res.getDrawable(R.drawable.marker_red);
			}

			markers.get(i).setMarker(markerImage);
		}

		stationsMarkersOverlay = new MarkerOverlay<ExtendedOverlayItem>(
				getApplicationContext(), markers, mapView,
				new CustomInfoWindow(mapView, getApplicationContext()));

		mapView.getOverlays().add(stationsMarkersOverlay);
	}

//	class MapOverlay extends Overlay
//	{
//
//		private float x;
//		private float y;
//
//		public MapOverlay(Context ctx)
//		{
//			super(ctx);
//		}
//
//		@Override
//		public boolean onTouchEvent(MotionEvent event, MapView mapView)
//		{
//			
//			if (event.getAction() == event.ACTION_DOWN)
//			{
//				x = event.getX();
//				y = event.getY();
//			}
//			else if (event.getAction() == event.ACTION_UP)
//			{
//				if ((Math.abs(event.getX() - x) <= 10)
//						&& (Math.abs(event.getY() - y) <= 10))
//				{
//					closeOpenBubble();
//					return true;
//				}
//			}
//			return false;
//		}
//
//		public void closeOpenBubble()
//		{
//			Log.d("debug", "pressed");
//		}
//
//		@Override
//		protected void draw(Canvas arg0, MapView arg1, boolean arg2)
//		{
//			// TODO Auto-generated method stub
//
//		}
//	}
}
