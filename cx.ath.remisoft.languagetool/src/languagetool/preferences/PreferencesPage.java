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
package languagetool.preferences;

import languagetool.Activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.texteditor.spelling.IPreferenceStatusMonitor;
import org.eclipse.ui.texteditor.spelling.ISpellingPreferenceBlock;
import org.languagetool.Language;

public class PreferencesPage implements ISpellingPreferenceBlock {
	
	Combo language;
	IPreferenceStore store;
	
	@Override
	public Control createControl(Composite parent) {
		store = Activator.getDefault().getPreferenceStore();
		
		Composite inner= new Composite(parent, SWT.NONE);
		inner.setLayout(new GridLayout(3, false));
		
		Label label= new Label(inner, SWT.CENTER);
		label.setText("LanguageTool Options");
				
		this.language = new Combo(inner, SWT.CENTER);
		for (Language lang : Language.getAllLanguages())
		{
			language.add(lang.getName());
		}
		this.language.select(this.language.indexOf(store.getString(PreferenceConstants.P_LANGUAGE)));
		
		return inner;
	}

	@Override
	public void initialize(IPreferenceStatusMonitor statusMonitor) {
	}

	@Override
	public void setEnabled(boolean enabled) {
	}

	@Override
	public boolean canPerformOk() {
		return true;
	}

	@Override
	public void performOk() {
		this.store.setValue(PreferenceConstants.P_LANGUAGE, this.language.getText());
	}

	@Override
	public void performDefaults() {
		this.language.select(this.language.indexOf(store.getDefaultString(PreferenceConstants.P_LANGUAGE)));
	}

	@Override
	public void performRevert() {
		this.language.select(this.language.indexOf(store.getString(PreferenceConstants.P_LANGUAGE)));
	}

	@Override
	public void dispose() {
	}
}
