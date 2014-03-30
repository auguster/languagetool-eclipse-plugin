package languagetool.spellcheck;

import java.io.IOException;
import java.util.List;

import languagetool.Activator;
import languagetool.preferences.PreferenceConstants;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.texteditor.spelling.ISpellingEngine;
import org.eclipse.ui.texteditor.spelling.ISpellingProblemCollector;
import org.eclipse.ui.texteditor.spelling.SpellingContext;
import org.languagetool.JLanguageTool;
import org.languagetool.Language;
import org.languagetool.language.French;
import org.languagetool.rules.RuleMatch;

public class Engine implements ISpellingEngine {

	@Override
	public void check(IDocument document, IRegion[] regions, SpellingContext context, ISpellingProblemCollector collector, IProgressMonitor monitor) {

		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		
		// Replaces JLanguageTool default databrocker by one that works in
		// Eclipse Plugin context
		JLanguageTool.setDataBroker(new EclipseRessourceDataBroker());
		
		Language language = null;
		for (Language lang : Language.getAllLanguages())
		{
			if (lang.getName().equals(store.getString(PreferenceConstants.P_LANGUAGE)))
			{
				language = lang;
				break;
			}
		}
		
		if (language == null)
			return;

		JLanguageTool jlt = null;
		try {
			jlt = new JLanguageTool(language);
			jlt.activateDefaultPatternRules();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (jlt != null) {
			for (IRegion region : regions) {
				List<RuleMatch> matches;
				try {
					matches = jlt.check(document.get(region.getOffset(), region.getLength()));
					for (RuleMatch match : matches) {
						collector.accept(new LTSpellingProblem(match));
					}
				} catch (IOException | BadLocationException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
