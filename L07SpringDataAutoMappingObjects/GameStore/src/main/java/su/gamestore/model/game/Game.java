package su.gamestore.model.game;

import su.gamestore.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    @Column( nullable = false )
    private String title;

    @Column (name ="trailer_id",nullable = false)
    private String trailerId;

    @Column(name ="thumbnail_URL",nullable = false)
    private String thumbnailURL;

    @Column(nullable = false)
    private BigDecimal price;

    private float size;

    private String description;

    @Column(name = "release_date",nullable = false)
    private LocalDate releaseDate;

    @Column(nullable = false)
    private boolean deleted;
    public Game() {
        super();
        this.deleted=false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }



    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public void update(GameAddUpdateDTO updateGameDTO) {
        if(updateGameDTO.getTitle() != null) this.title = updateGameDTO.getTitle();
        if(updateGameDTO.getPrice() != null) this.price = updateGameDTO.getPrice();
        if(updateGameDTO.getSize() > 0) this.size = updateGameDTO.getSize();
        if(updateGameDTO.getTrailerId() != null) this.trailerId = updateGameDTO.getTrailerId();
        if(updateGameDTO.getThumbnailURL() != null) this.thumbnailURL = updateGameDTO.getThumbnailURL();
        if(updateGameDTO.getDescription() != null) this.description = updateGameDTO.getDescription();
        if(updateGameDTO.getReleaseDate() != null) this.releaseDate = updateGameDTO.getReleaseDate();
    }
}
