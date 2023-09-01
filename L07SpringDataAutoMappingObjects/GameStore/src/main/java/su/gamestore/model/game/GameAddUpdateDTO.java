package su.gamestore.model.game;

import su.gamestore.constants.OutputMessages;
import su.gamestore.utils.Patterns;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameAddUpdateDTO {
    private String title;
    private BigDecimal price;
    private float size;
    private String trailerId;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;


    private  List<String> validationMessages  ;
    public GameAddUpdateDTO() {
        this.validationMessages = new ArrayList<>();
    }

    public GameAddUpdateDTO(String[] params) {
        this();
        if (params[0].equals("EditGame")) {
            for (int i = 2; i < params.length; i++) {
                String field = params[i].split("=")[0];
                String value = params[i].split("=")[1];
                switch (field) {
                    case "price" -> this.setPrice(BigDecimal.valueOf(Double.parseDouble(value)));
                    case "size" -> this.setSize(Float.parseFloat(value));
                    case "trailer" -> this.setTrailerId(value);
                    case "thumbnailURL" -> this.setThumbnailURL(value);
                    case "description" -> this.setDescription(value);
                    case "title" -> this.setTitle(value);
                    default -> this.validationMessages.add(OutputMessages.GAME_NO_SUCH_FIELD);
                }
            }
        } else {
            this.setTitle( params[1]);
            this.setPrice(BigDecimal.valueOf(Double.parseDouble(params[2])));
            this.setSize( Float.parseFloat(params[3]));
            this.setTrailerId( params[4]);
            this.setThumbnailURL(  params[5]);
            this.setDescription( params[6]);
            this.releaseDate = LocalDate.parse(params[7], DateTimeFormatter.ofPattern(Patterns.DATE_FORMAT));
        }



    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        validateTitle();
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.signum() <= 0) {
            validationMessages.add(OutputMessages.GAME_PRICE_NOT_VALID);
        }
        this.price = price;
    }

    public float getSize() {
    return size;
    }

    public void setSize(float size) {
        if (this.size <= 0) {
            validationMessages.add(OutputMessages.GAME_SIZE_NOT_VALID);
        }
        this.size = size;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        if (!Patterns.GAME_YOUTUBE_ID_REGEX.matcher(this.trailerId).find()) {
            validationMessages.add(OutputMessages.GAME_INVALID_YOUTUBE_ID);
        }this.trailerId = trailerId;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {

        if (!(this.thumbnailURL.startsWith("https://") || this.thumbnailURL.startsWith("http://"))) {
            validationMessages.add(OutputMessages.GAME_WRONG_THUMBNAIL_URL);
        }
        this.thumbnailURL = thumbnailURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() < 20) {
            validationMessages.add(OutputMessages.GAME_DESCRIPTION_TOO_SHORT);
        }
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }



    public List<String> getValidationMessages() {
        return Collections.unmodifiableList(validationMessages);
    }

    public void setValidationMessages(List<String> validationMessages) {
        this.validationMessages = validationMessages;
    }

    private void validateTitle() {
        if (title.isBlank() || title.isEmpty() || title.length() < 3) {
            validationMessages.add(OutputMessages.GAME_TITLE_TOO_SHORT);
        }
        if (title.length() > 30) {
            validationMessages.add(OutputMessages.GAME_TITLE_TOO_LONG);
        }
        if (!Patterns.GAME_TITLE_REGEX.matcher(title).find()) {
            validationMessages.add(OutputMessages.GAME_TITLE_MUST_START_WITH_UPPERCASE);
        }
    }
}
