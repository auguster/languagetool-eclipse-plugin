package languagetool.spellcheck;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.languagetool.databroker.DefaultResourceDataBroker;

public class EclipseRessourceDataBroker extends DefaultResourceDataBroker {

	public EclipseRessourceDataBroker() {
		super();
	}

	public EclipseRessourceDataBroker(String resourceDir, String rulesDir) {
		super(resourceDir, rulesDir);
	}

	public URL getFromResourceDirAsUrl(final String path)
	{
		try {
			return FileLocator.resolve(super.getFromResourceDirAsUrl(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
