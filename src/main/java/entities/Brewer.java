package entities;

import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.ALL;

@Data
@Entity(name = "Brewers")
public class Brewer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String zipCode;

    @Column
    private String city;

    @Column
    private int turnover;

    @OneToMany(mappedBy = "brewer", orphanRemoval = true)
    @Cascade(ALL)
    private List<Beer> beers = new ArrayList<>();

    @Override
    public String toString() {
        return "Brewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", turnover=" + turnover +
                '}';
    }
}
