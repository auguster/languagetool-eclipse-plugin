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
