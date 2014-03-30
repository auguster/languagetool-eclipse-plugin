package languagetool.spellcheck;

import java.util.List;

import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.ui.texteditor.spelling.SpellingProblem;
import org.languagetool.rules.RuleMatch;

public class LTSpellingProblem extends SpellingProblem {

	private RuleMatch ruleMatch; 
	
	public LTSpellingProblem(RuleMatch ruleMatch) {
		this.ruleMatch = ruleMatch;
	}

	@Override
	public int getOffset() {
		return ruleMatch.getOffset();
	}

	@Override
	public int getLength() {
		return ruleMatch.getToPos() - ruleMatch.getFromPos();
	}

	@Override
	public String getMessage() {
		return ruleMatch.getMessage();
	}

	@Override
	public ICompletionProposal[] getProposals() {
		List<String> replacements = ruleMatch.getSuggestedReplacements();
		CompletionProposal[] proposals = new CompletionProposal[replacements.size()];
		int i = 0;
		for (String replacement : replacements)
		{
			proposals[i++] = new CompletionProposal(replacement, getOffset(), getLength(), ruleMatch.getToPos());
		}
		
		return proposals;
	}

}
