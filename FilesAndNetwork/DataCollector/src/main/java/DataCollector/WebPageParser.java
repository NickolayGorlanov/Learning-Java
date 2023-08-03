package DataCollector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebPageParser {
    private final String url;

    public WebPageParser(String url) {
        this.url = url;
    }

    public String getHTML() throws IOException {
        return Jsoup.connect(url).get().html();
    }

    public List<SubwayLine> parseSubwayLines(String html) {
        Document doc = Jsoup.parse(html);
        Elements lineElements = doc.select("span.js-metro-line.t-metrostation-list-header.t-icon-metroln[data-line]");

        List<SubwayLine> subwayLines = new ArrayList<>();
        for (Element lineElement : lineElements) {
            String name = lineElement.text().trim();
            String number = lineElement.attr("data-line").trim();
            subwayLines.add(new SubwayLine(name, number));
        }

        return subwayLines;
    }

    public List<SubwayStation> parseSubwayStations(String html) {
        Document doc = Jsoup.parse(html);
        Elements stationElements = doc.select("div.js-metro-stations.t-metrostation-list-table[data-line] p.single-station");

        List<SubwayStation> subwayStations = new ArrayList<>();
        for (Element stationElement : stationElements) {
            String name = stationElement.select(".name").text().trim();
            String lineNumber = stationElement.select(".num").text().trim();
            subwayStations.add(new SubwayStation(name, lineNumber));
        }

        return subwayStations;
    }
}
