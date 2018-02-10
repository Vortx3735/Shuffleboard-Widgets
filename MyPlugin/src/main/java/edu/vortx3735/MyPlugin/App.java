package edu.vortx3735.MyPlugin;

import java.util.List;
import de.codecentric.centerdevice.javafxsvg.*;

import com.google.common.collect.ImmutableList;

import edu.wpi.first.shuffleboard.api.plugin.Description;
import edu.wpi.first.shuffleboard.api.plugin.Plugin;
import edu.wpi.first.shuffleboard.api.widget.ComponentType;
import edu.wpi.first.shuffleboard.api.widget.WidgetType;

@Description(group = "VorTX 3735", name = "VorTx3735 Plugins", summary = "Robot 2018 Plugins", version = "1.0.0")
public class App extends Plugin 
{
	@Override
	public List<ComponentType> getComponents() {

	return ImmutableList.of (WidgetType.forAnnotatedWidget (MyWidget.class) );
	}

	@Override
	public void onLoad() {
		SvgImageLoaderFactory.install();
	}
}
