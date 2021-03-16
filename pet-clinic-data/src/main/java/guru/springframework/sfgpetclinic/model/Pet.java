package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Builder
    public Pet(Long id, LocalDate birthDate, PetType petType, Owner owner, String name, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.petType = petType;
        this.owner = owner;
        this.name = name;
        if(visits == null || visits.size() > 0){
            this.visits = visits;
        }

    }

    @Column(name = "birth_date")
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits=new HashSet<>();



}
