package su.advquerying.entities;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "labels")
public class Label extends BaseEntity {

    private String title;
    private String subtitle;
    private Set<su.advquerying.entities.Shampoo> shampoos;

    public Label() {
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "subtitle")
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @OneToMany(mappedBy = "label", targetEntity = su.advquerying.entities.Shampoo.class)
    public Set<su.advquerying.entities.Shampoo> getShampoos() {
        return this.shampoos;
    }

    public void setShampoos(Set<su.advquerying.entities.Shampoo> shampoos) {
        this.shampoos = shampoos;
    }

    @Override
    public String toString() {
        return "Label{ id='" +this.getId() +
                "', title='" + title + '\'' +
                ", subtitle='" + subtitle +   '}';
    }
}
