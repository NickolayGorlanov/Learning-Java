package searchengine.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('INDEXING', 'INDEXED', 'FAILED')", nullable = false)
    private SiteStatus status;

    @Column(name = "status_time", nullable = false)
    private LocalDateTime statusTime;

    @Column(name = "last_error")
    private String lastError;

    @Column(name = "url", length = 255, nullable = false)
    private String url;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Page> pages;

    public Long getId() {
        return id;
    }

    public SiteStatus getStatus() {
        return status;
    }

    public LocalDateTime getStatusTime() {
        return statusTime;
    }

    public String getLastError() {
        return lastError;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(SiteStatus siteStatus) {
        this.status = siteStatus;
    }

    public void setLastError(String errorMessage) {
        this.lastError = errorMessage;
    }


    public void setStatusTime(LocalDateTime now) {
    }
}
