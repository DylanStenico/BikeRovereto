package smartcampus.model;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import eu.trentorise.smartcampus.osm.android.util.BoundingBoxE6;
import eu.trentorise.smartcampus.osm.android.util.GeoPoint;

public class Station implements Parcelable
{
	private GeoPoint position;
	String description;
	
	int usedSlots;
	int maxSlots;
	
	
	public Station(GeoPoint position, String description, int maxSlots)
	{
		this.position = position;
		this.description = description;
		this.maxSlots = maxSlots;
		this.usedSlots = 0;
	}

	public static BoundingBoxE6 getBoundingBox(ArrayList<Station> stations)
	{
		//the four edges of the bounding box

		int north = Integer.MIN_VALUE;
		int south = Integer.MAX_VALUE;
		int west  = Integer.MAX_VALUE;
		int east  = Integer.MIN_VALUE;

		for (Station s : stations)
		{
			if (s.getPosition().getLatitudeE6() > north)
			{
				north = s.getPosition().getLatitudeE6();
			}
			else if (s.getPosition().getLatitudeE6() < south)
			{
				south = s.getPosition().getLatitudeE6();
			}

			if (s.getPosition().getLongitudeE6() > east)
			{
				east = s.getPosition().getLongitudeE6();
			}
			else if (s.getPosition().getLongitudeE6() < west)
			{
				west = s.getPosition().getLongitudeE6();
			}
		}
		return new BoundingBoxE6(north, east, south, west);
	}

	// parcelable stuff
	public Station(Parcel source)
	{
		position = new GeoPoint(source.readInt(), source.readInt());
		description = source.readString();
		usedSlots = source.readInt();
		maxSlots = source.readInt();
	}

	public static final Parcelable.Creator<Station> CREATOR = new Creator<Station>()
	{

		@Override
		public Station[] newArray(int size)
		{
			return new Station[size];
		}

		@Override
		public Station createFromParcel(Parcel source)
		{
			return new Station(source);
		}
	};

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		//Log.d("parcelable", Double.toString(position.getLongitudeE6()));
		//Log.d("parcelable", Double.toString(position.getLongitudeE6()));
		//Log.d("parcelable", "-----");
		
		dest.writeInt(position.getLatitudeE6());
		dest.writeInt(position.getLongitudeE6());
		dest.writeString(description);
		dest.writeInt(usedSlots);
		dest.writeInt(maxSlots);
	}
	
	
	//getters and setters
	public GeoPoint getPosition()
	{
		return new GeoPoint(position.getLatitudeE6(), position.getLongitudeE6());
	}

	public String getDescription()
	{
		return new String(description);
	}
	
	public double getUsedPercentage()
	{
		if(usedSlots == 0)
		{
			return 0;
		}
		return (double) usedSlots/maxSlots;
	}
	
	public void setUsedSlots(int usedSlots)
	{
		
		if(usedSlots > maxSlots)
		{
			throw new RuntimeException("slots out of bounds");
		}
		this.usedSlots = usedSlots;
	}
}
