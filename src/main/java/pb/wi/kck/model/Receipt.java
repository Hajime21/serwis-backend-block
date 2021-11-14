package pb.wi.kck.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor; //?

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Receipt {
    @Id private int receiptId;
    private float receiptValue;
    private long purchaseDate;
    private String shopName;
    private int[] purchasedProductsIds; //tablica productId produktow
    private float[] productsPrices; //tablica cen odpowiadających tablicy zakupy

}
