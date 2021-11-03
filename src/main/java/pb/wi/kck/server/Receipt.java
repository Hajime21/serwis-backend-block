package pb.wi.kck.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor; //?

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Receipt {
    private int receiptId;
    private float receiptValue;
    private long purchaseDate;
    private String shopName;
    private int[] purchasedProductsIds; //tablica productId produktow
    private float[] productsPrices; //tablica cen odpowiadających tablicy zakupy

}
