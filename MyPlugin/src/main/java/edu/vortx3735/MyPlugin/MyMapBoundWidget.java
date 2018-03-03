package edu.vortx3735.MyPlugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.wpi.first.shuffleboard.api.data.MapData;
import edu.wpi.first.shuffleboard.api.data.types.MapType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@Description(dataTypes = { MapType.class }, name = "My Map Bound Widget")
@ParametrizedController(value = "MyMapBoundWidget.fxml")
public class MyMapBoundWidget extends SimpleAnnotatedWidget<MapData> implements ChangeListener<MapData> {

	@FXML
	private AnchorPane _thePane;
	
	@FXML
	private ImageView _mapImageView;

	private final SimpleStringProperty _xKey = new SimpleStringProperty(this, "xKey", "x");
	private final SimpleStringProperty _yKey = new SimpleStringProperty(this, "yKey", "y");
	private final SimpleStringProperty _yawKey = new SimpleStringProperty(this, "yawKey", "yaw");
	private final SimpleBooleanProperty _mapKey = new SimpleBooleanProperty(this, "mapKey", true);
	
	
	public MyMapBoundWidget ()
	{
		// Export the properties to set the key value to bind to each gauge.
		exportProperties(_xKey, _yKey, _yawKey, _mapKey);
		
		// Set up a listener that gets triggered every time the map is updated.
		dataProperty().addListener(this);
		_mapKey.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				setImage(newValue);
			}
		});
	}

	
	public String getXKey ()
	{
		return _xKey.getValue();
	}
	
	public void setXKey (String top)
	{
		_xKey.setValue(top);
	}
	
	public String getYKey ()
	{
		return _yKey.getValue();
	}
	
	public void setYKey (String middle)
	{
		_yKey.setValue(middle);
	}
	
	public String getYawKey ()
	{
		return _yawKey.getValue();
	}
	
	public void setYawKey (String bottom)
	{
		_yawKey.setValue(bottom);
	}
	
	public Boolean getMapKey ()
	{
		return _mapKey.getValue();
	}
	
	public void setMapKey (Boolean bottom)
	{
		_mapKey.setValue(bottom);
	}
	

	@Override
	public Pane getView() {

		return _thePane;
	}

	
	/**
	 * Sets the value of the specified gauge control with some validation to make sure the gauge control
	 * is assigned to a value in the map.
	 * 
	 * @param g The gauge control to set the value in.
	 * @param key The key value from the map to find the data to set in the gauge.
	 */

	public void setImage(boolean isRed) {
		if (isRed)
		{
			_mapImageView.setImage(new Image(getClass().getResource("/redDown.svg").toExternalForm()));
		} else {
			_mapImageView.setImage(new Image(getClass().getResource("/blueDown.svg").toExternalForm()));
		}
	}
	
	@Override
	public void changed(ObservableValue<? extends MapData> arg0, MapData arg1, MapData arg2) {
		
	}

}
