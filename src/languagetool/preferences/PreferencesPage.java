package languagetool.preferences;

import languagetool.Activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
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
