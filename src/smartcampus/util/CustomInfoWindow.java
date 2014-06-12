package smartcampus.util;

//import org.osmdroid.bonuspack.overlays.DefaultInfoWindow;
//import org.osmdroid.bonuspack.overlays.ExtendedOverlayItem;
//import org.osmdroid.util.GeoPoint;
//import org.osmdroid.views.MapView;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.openstreetmap.R;

import eu.trentorise.smartcampus.osm.android.bonuspack.overlays.DefaultInfoWindow;
import eu.trentorise.smartcampus.osm.android.bonuspack.overlays.ExtendedOverlayItem;
import eu.trentorise.smartcampus.osm.android.views.MapView;

public class CustomInfoWindow extends DefaultInfoWindow
{
	Context mContext;
	MapView myMapView;

	public CustomInfoWindow(MapView mapView, Context context)
	{
		super(R.layout.info_bubble, mapView);
		mContext = context;
		myMapView = mapView;
		// Button btn = (Button) (mView.findViewById(R.id.bubble_moreinfo));
		// btn.setOnClickListener(new View.OnClickListener()
		// {
		// public void onClick(View v)
		// {
		// Toast.makeText(v.getContext(), "Button clicked",
		// Toast.LENGTH_LONG).show();
		// if (isACavemarker)
		// {
		// if (Osm_Map.startSelectingForRouting)
		// {
		// Toast.makeText(v.getContext(), "added",
		// Toast.LENGTH_LONG).show();
		// Osm_Map.waypoints.add(new GeoPoint(MainActivity.mCaves
		// .get(Osm_Map.bubbleIndexShown(isACavemarker))
		// .getLatitude(), MainActivity.mCaves.get(
		// Osm_Map.bubbleIndexShown(isACavemarker))
		// .getLongitude()));
		// } else
		// {
		// Osm_Map.waypoints.clear();
		// Intent detailsintent = new Intent(mContext,
		// CaveDetails.class);
		// detailsintent.putExtra("SELECTED_CAVE",
		// MainActivity.mCaves.get(Osm_Map
		// .bubbleIndexShown(isACavemarker)));
		// detailsintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// mContext.startActivity(detailsintent);
		// }
		// } else
		// {
		// if (Osm_Map.startSelectingForRouting)
		// {
		// Toast.makeText(v.getContext(), "added",
		// Toast.LENGTH_LONG).show();
		// Osm_Map.waypoints.add(new GeoPoint(
		// Osm_Map.markersFindByGeocodingOverlay
		// .getItem(Osm_Map
		// .bubbleIndexShown(isACavemarker)).mGeoPoint
		// .getLatitudeE6() / 1E6,
		// Osm_Map.markersFindByGeocodingOverlay.getItem(Osm_Map
		// .bubbleIndexShown(isACavemarker)).mGeoPoint
		// .getLongitudeE6() / 1E6));
		// } else
		// Osm_Map.waypoints.clear();
		// }
		// }
		// });
	}

	@Override
	public void open(ExtendedOverlayItem item, int offsetX, int offsetY)
	{
		// TODO Auto-generated method stub
		Log.d("debug2", "opened");
		super.open(item, offsetX + 15, offsetY + 80);
	}

	@Override
	public void onOpen(ExtendedOverlayItem item)
	{
		super.onOpen(item);
		// mView.findViewById(R.id.bubble_moreinfo).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel00).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel01).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel02).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel03).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel04).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel05).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel06).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel07).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel08).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel09).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel10).setVisibility(View.VISIBLE);
		mView.findViewById(R.id.wheel11).setVisibility(View.VISIBLE);
	}
	
	@Override
	public void close()
	{
		Log.d("debug2", "removed");
		super.close();
	}
}