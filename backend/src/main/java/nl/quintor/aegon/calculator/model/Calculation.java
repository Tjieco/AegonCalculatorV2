package nl.quintor.aegon.calculator.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int first;
    private int second;
    private CalculationType type;

    @Nullable
    private double result;

    public Calculation(int first, int second, CalculationType type) {
        this.first = first;
        this.second = second;
        this.type = type;
    }
}
