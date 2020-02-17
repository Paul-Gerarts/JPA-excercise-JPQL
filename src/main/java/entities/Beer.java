package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Data
@NoArgsConstructor
@Entity(name = "Beers")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private float price;
    private int stock;
    private float alcohol;
    private int version;
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "BrewerId")
    private Brewer brewer;

    public Beer(String name, float price, int stock, float alcohol, int version, byte[] image, Category category, Brewer brewer) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.alcohol = alcohol;
        this.version = version;
        this.image = image;
        this.category = category;
        this.brewer = brewer;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                ", version=" + version +
                ", image=" + Arrays.toString(image) +
                ", catergoryId" + category.getId() +
                ", brewerId" + brewer.getId() +
                '}';
    }
}
