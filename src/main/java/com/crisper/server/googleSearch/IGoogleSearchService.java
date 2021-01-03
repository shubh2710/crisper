package com.crisper.server.googleSearch;

import com.crisper.server.googleSearch.model.GoogleSearchRequest;
import com.crisper.server.googleSearch.model.GoogleSearchResponse;

public interface IGoogleSearchService {
    public GoogleSearchResponse process(GoogleSearchRequest request);
}
