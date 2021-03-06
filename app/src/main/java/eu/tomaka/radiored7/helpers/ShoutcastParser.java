package eu.tomaka.radiored7.helpers;

import java.io.IOException;
import java.net.URISyntaxException;
import net.moraleboost.streamscraper.ScrapeException;


/**
 * Created by tomek on 28.03.16.
 */
public class ShoutcastParser {
    public String getStreamGenere(String streamURL) throws IOException, URISyntaxException, ScrapeException {
        ShoutCastMetadataRetriever smr = new ShoutCastMetadataRetriever();
        smr.setDataSource(streamURL);
        String genre = smr.extractMetadata(ShoutCastMetadataRetriever.METADATA_KEY_GENRE);
        return genre;
    }
    public String getStreamTitle(String streamURL) throws IOException, URISyntaxException, ScrapeException {
        ShoutCastMetadataRetriever smr = new ShoutCastMetadataRetriever();
        smr.setDataSource(streamURL);
        String title = smr.extractMetadata(ShoutCastMetadataRetriever.METADATA_KEY_TITLE);
        return title;
    }

}
