package edu.vortx3735.MyPlugin;

import java.net.URL;

import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

@Description(dataTypes = { Boolean.class }, name = "Test SVG Widget")
@ParametrizedController(value = "MyWidget.fxml")
public class MyWidget extends SimpleAnnotatedWidget {

	@FXML
	protected Pane _thePane;
	
	int _neg = 0;
	int _pos = 0;
	
	@FXML
	ImageView _imgView;
	
	public Pane getView() {
//		URL url = getClass().getResource("/edu/vortx3735/MyPlugin/man.jpg");
//		
//		Image  i = new Image (url);
//		
//		_imgView.setImage(i);
//		_imgView.setFitWidth(100.0);
//		_imgView.setFitHeight(100.0);
		return _thePane;
	}

	
	@FXML
	protected void onButtonPress(ActionEvent e)
	{
		_neg--;
		_pos++;
		
	}
	
}
