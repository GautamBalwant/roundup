package com.roundup.roundup.model.client.transaction;

import com.roundup.roundup.model.client.ClientResponse;
import java.util.List;

/**
 * This class is used to hold the list of transaction feeds
 */
public class TransactionResponse extends ClientResponse {

    private List<FeedItem> feedItems;

    public List<FeedItem> getFeedItems() {
        return feedItems;
    }

    public void setFeedItems(List<FeedItem> feedItems) {
        this.feedItems = feedItems;
    }
}
