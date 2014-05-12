/*
Copyright (c) 2014 Rémi AUGUSTE <remi.auguste@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
