package pa.iscde.docGeneration;

import java.util.List;

import pa.iscde.search.model.MatchResult;
import pa.iscde.search.services.SearchListener;

/**
 * Class which implements Search's listeners and it's actions
 * @author Ricardo Silva
 *
 */
public class SearchListenersActions implements SearchListener {

	/**
	 * Action performed when a search is completed in the search Component
	 * @param searchinput String word that was search in the Search Component
	 * @param resultList List results gotten from the search action
	 */
	@Override
	public void searchComplete(String searchInput, List<MatchResult> resultList) {
		DocGenView.getInstance().SearchWord(searchInput);
	}

}
