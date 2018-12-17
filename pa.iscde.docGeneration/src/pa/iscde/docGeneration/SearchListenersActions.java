package pa.iscde.docGeneration;

import java.util.List;

import pa.iscde.search.model.MatchResult;
import pa.iscde.search.services.SearchListener;

public class SearchListenersActions implements SearchListener {

	@Override
	public void searchComplete(String searchInput, List<MatchResult> resultList) {
		
		DocGenView.getInstance().SearchWord(searchInput);
	}

}
