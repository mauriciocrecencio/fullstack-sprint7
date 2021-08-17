package mother;

import br.com.rchlo.store.domain.Color;
import br.com.rchlo.store.domain.Product;
import java.math.BigDecimal;

public class ProductMother {

  public static Product aBlueJacket() {
    return new Product(7L,
        "Jaqueta Puffer Juvenil Com Capuz Super Mario Branco",
        "A Jaqueta Puffer Juvenil Com Capuz Super Mario Branco é confeccionada em material sintético.",
        "jaqueta-puffer-juvenil-com-capuz-super-mario-branco-13834193_sku",
        "Nintendo",
        new BigDecimal("199.90"),
        null,
        Color.WHITE,
        147);
  }

  public static Product aCheapTankTop() {
    return new Product(1L,
        "Regata Infantil Mario Bros Branco",
        "A Regata Infantil Mario Bros Branco é confeccionada em fibra natural. Aposte!",
        "regata-infantil-mario-bros-branco-14040174_sku",
        "Nintendo",
        new BigDecimal("29.90"),
        null,
        Color.WHITE,
        98);
  }
public static Product aTShirt() {
    return new Product(5L,
        "Camiseta Azul",
        "A camiseta azul mais bonita do Brasil",
        "rcamiseta-azul-mais-bonita-14040174_sku",
        "Camisetona",
        new BigDecimal("19.90"),
        null,
        Color.BLUE,
        98);
  }

}
