package searchengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveSite(Site site) {
        jdbcTemplate.update(
                "INSERT INTO site (status, status_time, last_error, url, name) VALUES (?, ?, ?, ?, ?)",
                site.getStatus().toString(), site.getStatusTime(), site.getLastError(), site.getUrl(), site.getName()
        );
    }

    public void savePage(Page page) {
        jdbcTemplate.update(
                "INSERT INTO page (site_id, path, code, content) VALUES (?, ?, ?, ?)",
                page.getSite().getId(), page.getPath(), page.getCode(), page.getContent()
        );
    }

    public void updateSite(Site site) {
    }

    public void deleteSiteData(String baseUrl) {
    }

    public boolean isPageVisited(String url) {
        return false;
    }
}
