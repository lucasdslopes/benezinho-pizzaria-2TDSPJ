package br.com.fiap.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "TB_PRODUTO")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PRODUTO")
    @SequenceGenerator(
            name = "SQ_PRODUTO",
            sequenceName = "SQ_PRODUTO",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_PRODUTO")
    private Long id;


    @Column(name = "NM_PRODUTO")
    private String nome;

    @Column(name = "VL_PRODUTO")
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(
            name = "SABOR_ID",
            referencedColumnName = "ID_SABOR",
            foreignKey = @ForeignKey(name = "FK_SABOR_PRODUTO")
    )
    private Sabor sabor;

    @ManyToMany
    @JoinTable(
            name = "TB_OPCIONAL_PRODUTO",
            joinColumns = {
                    @JoinColumn(
                            name = "PRODUTO",
                            referencedColumnName = "ID_PRODUTO",
                            foreignKey = @ForeignKey(name = "FK_PRODUTO_OPCIONAL")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "OPCIONAL",
                            referencedColumnName = "ID_OPCIONAL",
                            foreignKey = @ForeignKey(name = "FK_OPCIONAL_PRODUTO")
                    )
            }
    )
    private Set<Opcional> opcionais = new LinkedHashSet<>();

}
