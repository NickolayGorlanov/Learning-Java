package searchengine.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "sites")
public class SiteConfigurations {
    private List<SiteConfig> sites;

    public List<SiteConfig> getSites() {
        return sites;
    }

    public void setSites(List<SiteConfig> sites) {
        this.sites = sites;
    }
}
