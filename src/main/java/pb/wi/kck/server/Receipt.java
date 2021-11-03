package pb.wi.kck.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor; //?

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Receipt {
    @NonNull private int receiptId;
    @NonNull private float receiptValue;
    @NonNull private long purchaseDate;
    @NonNull private String shopName;
    private int[] purchasedProductsIds; //tablica productId produktow
    private float[] productsPrices; //tablica cen odpowiadających tablicy zakupy

}
